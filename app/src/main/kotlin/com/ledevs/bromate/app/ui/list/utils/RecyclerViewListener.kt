package com.ledevs.bromate.app.ui.list.utils

import android.support.v7.widget.RecyclerView
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import java.lang.ref.WeakReference
import java.util.concurrent.atomic.AtomicInteger

class RecyclerViewListener(
    recyclerView: RecyclerView
) {

  private val reference = WeakReference(recyclerView)

  private val offsetScrollListener = Flowable.create<Int>({ emitter ->
    val totalScroll = AtomicInteger()
    val listener = object : RecyclerView.OnScrollListener() {
      override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        emitter.onNext(totalScroll.addAndGet(dy))
      }
    }

    reference.get()?.addOnScrollListener(listener)
    emitter.setCancellable { reference.get()?.removeOnScrollListener(listener) }

  }, BackpressureStrategy.LATEST).share()

  fun offsetScrollsChanges(): Flowable<Int> {
    return offsetScrollListener

  }
}

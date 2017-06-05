package com.ledevs.bromate.app.ui.view

import android.content.Context
import android.support.annotation.StringRes
import android.support.v4.view.ViewCompat
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.support.v4.view.animation.LinearOutSlowInInterpolator
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import com.ledevs.bromate.R
import io.reactivex.Observable


class FeedbackView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attributeSet, defStyleAttr) {

  private val errorView by lazy { findViewById(R.id.error_view) }
  private val loadingView by lazy { findViewById(R.id.loading_view) }
  private val retryButton by lazy { findViewById(R.id.retry_button) as Button }
  private val errorTitle by lazy { findViewById(R.id.error_title) as TextView }
  private val errorMessage by lazy { findViewById(R.id.error_message) as TextView }

  val fadeInAnimTime by lazy { resources.getInteger(android.R.integer.config_mediumAnimTime).toLong() }
  val fadeOutAnimTime by lazy { resources.getInteger(android.R.integer.config_shortAnimTime).toLong() }

  private var viewState = ViewState.IDLE

  init {
    LayoutInflater.from(context).inflate(R.layout.view_feedback, this)

    ViewCompat.setAlpha(errorView, 0f)
    ViewCompat.setAlpha(loadingView, 0f)
  }

  fun showError(title: String, message: String) {
    viewState = ViewState.ERROR

    errorTitle.text = title
    errorMessage.text = message

    when {
      loadingView.isShown -> hideView(loadingView) { showView(errorView, viewState) }
      else -> showView(errorView, viewState)
    }
  }

  fun showError(@StringRes title: Int, @StringRes message: Int) {
    showError(resources.getString(title), resources.getString(message))
  }

  fun showLoading() {
    viewState = ViewState.LOADING

    when {
      errorView.isShown -> hideView(errorView) { showView(loadingView, viewState) }
      else -> showView(loadingView)
    }
  }

  fun retryEvents(): Observable<View> {
    return Observable.create { publisher ->
      retryButton.setOnClickListener { publisher.onNext(it) }
      publisher.setCancellable { retryButton.setOnClickListener(null) }
    }
  }

  fun hideLoading() {
    viewState = ViewState.IDLE
    hideView(loadingView)
  }

  fun hideError() {
    viewState = ViewState.IDLE
    hideView(errorView)
  }

  private fun showView(view: View, stateOnCall: ViewState = viewState, endAction: (() -> Unit)? = null) {
    if (viewState == stateOnCall) {
      view.visibility = View.VISIBLE

      ViewCompat.animate(view)
          .alpha(1f)
          .setDuration(fadeInAnimTime)
          .setInterpolator(FastOutSlowInInterpolator())
          .withEndAction {
            endAction?.invoke()
          }
          .start()
    }
  }

  private fun hideView(view: View, stateOnCall: ViewState = viewState, endAction: (() -> Unit)? = null) {
    if (viewState == stateOnCall) {
      ViewCompat.animate(view)
          .alpha(0f)
          .setDuration(fadeOutAnimTime)
          .setInterpolator(LinearOutSlowInInterpolator())
          .withEndAction {
            view.visibility = View.GONE
            endAction?.invoke()
          }
          .start()
    }
  }

  enum class ViewState {
    IDLE, LOADING, ERROR
  }
}

package com.ledevs.bromate.utils


import android.content.res.Resources
import android.support.v7.widget.RecyclerView
import android.view.View

import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

/**
 * taken from https://gist.github.com/baconpat/8405a88d04bd1942eb5e430d33e4faa2
 * license MIT
 */
class RecyclerViewMatcher(private val recyclerViewId: Int) {

  fun atPosition(position: Int): Matcher<View> {
    return atPositionOnView(position, -1)
  }

  fun atPositionOnView(position: Int, targetViewId: Int): Matcher<View> {
    return object : TypeSafeMatcher<View>() {
      var resources: Resources? = null
      var childView: View? = null

      override fun describeTo(description: Description) {
        val idDescription = resources?.let { getIdDescription(it) }
        description.appendText("RecyclerView with id: $idDescription at position: $position")
      }

      override fun matchesSafely(view: View): Boolean {
        this.resources = view.resources

        if (childView == null) {
          val recyclerView = view.rootView.findViewById(recyclerViewId) as? RecyclerView
          if (recyclerView != null && recyclerView.id == recyclerViewId) {
            val viewHolder = recyclerView.findViewHolderForAdapterPosition(position)
            if (viewHolder != null) {
              childView = viewHolder.itemView
            }
          } else {
            return false
          }
        }

        if (targetViewId == -1) {
          return view === childView
        } else {
          val targetView = childView!!.findViewById(targetViewId)
          return view === targetView
        }
      }
    }

  }

  private fun getIdDescription(resources: Resources): String {
    return try {
      resources.getResourceName(recyclerViewId)
    } catch (var4: Resources.NotFoundException) {
      String.format("%s (resource name not found)", recyclerViewId)
    }
  }
}

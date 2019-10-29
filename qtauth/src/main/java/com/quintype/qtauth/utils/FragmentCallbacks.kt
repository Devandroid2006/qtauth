package com.quintype.qtauth.utils

import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView

interface FragmentCallbacks {
    fun addFragment(fragment: Fragment, backStack: String?, animationStyle: String)

    fun replaceFragment(fragment: Fragment, backStack: String?, animation: String)

    fun clickAnalyticsEvent(categoryId: String, actionId: String, labelId: String, value: Long)

    fun propagateEvent(event: Pair<String, Any>)

    fun getBottomBar(): BottomNavigationView?

    fun getAppBarLayout(): AppBarLayout?//added the to control the toolbar visibility on fragment add or replace

    fun getToolBar(): Toolbar?
}
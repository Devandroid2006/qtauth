package com.quintype.qtauth.fragments

import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.quintype.qtauth.R
import com.quintype.qtauth.utils.DebounceHandler
import com.quintype.qtauth.utils.FragmentCallbacks
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.custom_tool_bar.*

open class BaseFragment : Fragment() {
    val mCompositeDisposable = CompositeDisposable()
    var fragmentCallbacks: FragmentCallbacks? = null
    //    private var mTracker: Tracker? = null

    /*
     * onAttach(Context) is not called on pre API 23 versions of Android and onAttach(Activity) is deprecated
     * Use onAttachToContext instead
     */
    @TargetApi(23)
    override fun onAttach(context: Context) {
        super.onAttach(context)
        onAttachToContext(context)
    }

    /*
     * Deprecated on API 23
     * Use onAttachToContext instead
     */
    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            onAttachToContext(activity)
        }
    }

    /*
     * Called when the fragment attaches to the context
     */
    private fun onAttachToContext(context: Context) {
        if (context is FragmentCallbacks) {
            fragmentCallbacks = context
        } else {
            throw RuntimeException("$context must implement FragmentCallback methods")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //set the title for the toolbar
        refreshToolBarTitle()

    }

    override fun onDestroyView() {
        //dispose container
        mCompositeDisposable.dispose()
        DebounceHandler.clearAll()
        fragmentCallbacks = null
        super.onDestroyView()
    }

    /**
     * refresh the toolbar title
     */
    open fun refreshToolBarTitle() {
        if (!isAdded) {
            return
        }
        //update the title when it is visible to the user
        if (null != getToolBarTitle()) {
            //set the title for the toolbar
            fragmentCallbacks?.getToolBar()?.title = getToolBarTitle()
        } else {
            fragmentCallbacks?.getToolBar()?.title = ""
        }
    }

    /**
     * get simple tag name of the fragment class
     */
    fun getSimpleTAG(): String {
        return javaClass.simpleName
    }

    /**
     * get the title for the toolbar,
     * by default it will not show any title, override to show the required title on the toolbar
     */
    open fun getToolBarTitle(): String? {
        return null
    }

    fun setCustomActionBar(title: String?) {
        custom_tool_bar_ll_main_container?.visibility = View.VISIBLE
        custom_tool_bar_ll_main_container?.setBackgroundColor(
            ContextCompat.getColor(
                activity!!,
                R.color.white
            )
        )

        custom_tool_bar_iv_back_image?.visibility = View.VISIBLE
        custom_tool_bar_iv_back_image?.setImageDrawable(
            ContextCompat.getDrawable(
                activity!!,
                R.drawable.ic_left_arrow
            )
        )

        custom_tool_bar_iv_share_image?.visibility = View.GONE

        custom_tool_bar_tv_title?.isEnabled = false

        custom_tool_bar_tv_title?.text = title
        custom_tool_bar_iv_back_image?.setOnClickListener {
            activity?.onBackPressed()
        }
    }


}

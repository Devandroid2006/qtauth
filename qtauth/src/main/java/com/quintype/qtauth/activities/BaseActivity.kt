package com.quintype.qtauth.activities

import android.Manifest
import android.app.ProgressDialog
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.quintype.qtauth.fragments.BaseFragment
import com.quintype.qtauth.utils.FragmentCallbacks
import com.quintype.qtauth.R
import com.quintype.qtauth.utils.DebounceHandler
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit


abstract class BaseActivity : AppCompatActivity(), FragmentCallbacks,
    FragmentManager.OnBackStackChangedListener {

    private var mFragment: Fragment? = null
    private var activity: AppCompatActivity? = null
    var mBottomNavigationView: BottomNavigationView? = null
    var mToolBar: Toolbar? = null
    lateinit var pDialog: ProgressDialog
    val mCompositeDisposable = CompositeDisposable()

    // Progress dialog type (0 - for Horizontal progress bar)
    val progressBarType: Int = 0


    //    private val mTracker: Tracker? = null
//    var firebaseAnalytics: FirebaseAnalytics? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity = this
    }

    override fun onAttachFragment(fragment: Fragment) {
        super.onAttachFragment(fragment)
        //update the toolbar visibility
        getAppBarLayout()?.setExpanded(true, true)
    }

    override fun addFragment(fragment: Fragment, backStack: String?, animationStyle: String) {
        if (activity == null) {
            return
        }

        try {
            val addFragmentTransaction = fragmentTransactionAdd(animationStyle, fragment, backStack)
            addFragmentTransaction.commit()
        } catch (e: IllegalStateException) {
            val addFragmentTransaction = fragmentTransactionAdd(animationStyle, fragment, backStack)
            addFragmentTransaction.commitAllowingStateLoss()
        }
    }

    override fun replaceFragment(
        fragment: Fragment,
        backStack: String?,
        animationStyle: String
    ) {
        if (activity == null) {
            return
        }

        try {
            val replaceFragmentTransaction = fragmentTransactionReplace(animationStyle, fragment, backStack)
            replaceFragmentTransaction.commit()
        } catch (e: IllegalStateException) {
            val replaceFragmentTransaction = fragmentTransactionReplace(animationStyle, fragment, backStack)
            replaceFragmentTransaction.commitAllowingStateLoss()
        }
    }

    private fun fragmentTransactionAdd(animationStyle: String, fragment: Fragment, mBackStack: String?): FragmentTransaction {
        val fragmentManager = supportFragmentManager
        fragmentManager.addOnBackStackChangedListener(this)

        val fragmentTransaction = fragmentManager.beginTransaction()
        setAnimation(animationStyle, fragmentTransaction)
        fragmentTransaction.add(R.id.home_container, fragment)

        if (mBackStack != null) {
            fragmentTransaction.addToBackStack(mBackStack)
        }

        mFragment = fragment
        return fragmentTransaction
    }

    private fun fragmentTransactionReplace(
        animationStyle: String,
        fragment: Fragment,
        mBackStack: String?
    ): FragmentTransaction {
        val fragmentManager = supportFragmentManager
        fragmentManager.addOnBackStackChangedListener(this)
        val fragmentTransaction = fragmentManager.beginTransaction()

        setAnimation(animationStyle, fragmentTransaction)

        fragmentTransaction.replace(R.id.home_container, fragment)

        if (mBackStack != null) {
            fragmentTransaction.addToBackStack(mBackStack)
        }
        mFragment = fragment
        return fragmentTransaction
    }

    private fun setAnimation(animation: String, fragmentTransaction: FragmentTransaction) {
        when {
            animation == getString(R.string.SLIDE_DOWN) -> fragmentTransaction.setCustomAnimations(
                R.anim.slide_left_in_anim,
                R.anim.slide_left_out_anim,
                R.anim.slide_right_in_anim,
                R.anim.slide_right_out_anim
            )
            animation == getString(R.string.SLIDE_RIGHT) -> fragmentTransaction.setCustomAnimations(
                R.anim.slide_right_in_anim,
                R.anim.slide_right_out_anim,
                R.anim.slide_left_in_anim,
                R.anim.slide_left_out_anim
            )
            animation == getString(R.string.SLIDE_UP) -> fragmentTransaction.setCustomAnimations(
                R.anim.slide_up_in_anim,
                R.anim.slide_up_out_anim,
                R.anim.slide_down_in_anim,
                R.anim.slide_down_out
            )
            animation == getString(R.string.SLIDE_DOWN) -> fragmentTransaction.setCustomAnimations(
                R.anim.slide_down_in_anim,
                R.anim.slide_down_out,
                R.anim.slide_up_in_anim,
                R.anim.slide_up_out_anim
            )
            else -> fragmentTransaction.setCustomAnimations(0, 0)
        }
    }

    private fun hasWriteExternalStoragePermission(): Boolean {
        /*Check for runtime permission only for SDK versions above lollipop*/
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val result =
                ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            result == PackageManager.PERMISSION_GRANTED
        } else {
            /*Return true always if the API is less than 23*/
            true
        }
    }


    override fun clickAnalyticsEvent(categoryId: String, actionId: String, labelId: String, value: Long) {
    }

    override fun propagateEvent(event: Pair<String, Any>) {
    }

    override fun getBottomBar(): BottomNavigationView? {
        return mBottomNavigationView
    }

    override fun getToolBar(): Toolbar? {
        return mToolBar
    }

    /**
     * Make sure that when the up button in the action bar is pressed, the app goes
     * back to the screen it was previously on.
     *
     * @param item The menu item that was clicked
     * @return
     */
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        //handling toolbar back press
        if (android.R.id.home == item?.itemId) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        //check if there is only one fragment transaction in the backstack then quit the activity
        //otherwise popback one transaction
        val backStackCount = supportFragmentManager.backStackEntryCount
        if (backStackCount == 1) {
            finish()
        } else {
            super.onBackPressed()
        }
        //refresh toolbar title
        getToolBar()?.postDelayed({
            val fragment = supportFragmentManager.findFragmentById(R.id.home_container)
            if (fragment is BaseFragment && fragment.isAdded) {
                fragment.refreshToolBarTitle()
            }
        }, TimeUnit.MILLISECONDS.toMillis(200))
    }

    override fun onDestroy() {
        activity = null
        mCompositeDisposable.dispose()
        DebounceHandler.clearAll()
        super.onDestroy()
    }

    override fun getAppBarLayout(): AppBarLayout? {
        return null
    }

    override fun onResume() {
        /*Get font style from shared preferences and set in theme.*/
//        theme.applyStyle(Utilities().getFontStyle(applicationContext).resId, true)
        super.onResume()
    }

    override fun onBackStackChanged() {
        /*Have to do this to manage the options menu for each fragment.*/
        invalidateOptionsMenu()
    }

}
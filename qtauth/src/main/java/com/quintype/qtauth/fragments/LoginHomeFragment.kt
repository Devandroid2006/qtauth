package com.quintype.qtauth.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.quintype.qtauth.R
import com.quintype.qtauth.utils.DebounceHandler
import kotlinx.android.synthetic.main.fragment_login_home.*

class LoginHomeFragment : BaseFragment(), View.OnClickListener {
    fun newInstance(): LoginHomeFragment {
        val loginHomeFragment = LoginHomeFragment()
        val argument = Bundle()
        loginHomeFragment.arguments = argument
        return loginHomeFragment
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        more_container.visibility = View.GONE//set gone by default.

        more_options.setOnClickListener(this)
        email_container.setOnClickListener(this)
        fragment_login_home_iv_back.setOnClickListener(this)
        privacy_policy.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.more_options -> {
                more_options.visibility = View.GONE
                more_container.visibility = View.VISIBLE
            }
            R.id.email_container -> {
                val loginFragment = LoginFragment()
                fragmentCallbacks?.addFragment(loginFragment, loginFragment.getSimpleTAG(), resources.getString(R.string.SLIDE_LEFT))
            }
            R.id.fragment_login_home_iv_back -> {
                activity?.onBackPressed()
            }
            R.id.privacy_policy -> {
                DebounceHandler.handle(Runnable {
                    //TODO: Need to get the url from main module.
                    val fragment = WebViewFragment.get("https://www.quintype.com/privacy-policy")
                    fragmentCallbacks?.addFragment(fragment, fragment.getSimpleTAG(), resources.getString(R.string.SLIDE_LEFT))
                })
            }
        }
    }


}

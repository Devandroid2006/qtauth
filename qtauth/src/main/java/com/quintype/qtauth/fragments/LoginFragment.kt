package com.quintype.qtauth.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.quintype.qtauth.R
import com.quintype.qtauth.models.MemberRequestModel
import com.quintype.qtauth.utils.NetworkUtils
import com.quintype.qtauth.viewmodels.LoginManagerViewModel
import kotlinx.android.synthetic.main.login_fragment.*

class LoginFragment : BaseFragment(), View.OnClickListener {
    lateinit var loginViewModel: LoginManagerViewModel

    fun newInstance(): LoginFragment {
        val loginFragment = LoginFragment()
        val arguments = Bundle()
        loginFragment.arguments = arguments
        return loginFragment
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var loginFactory = LoginManagerViewModel.Factory()
        loginViewModel = ViewModelProviders.of(this, loginFactory).get(LoginManagerViewModel::class.java)

        fragment_login_iv_back.setOnClickListener(this)
        login_fragment_tv_sign_up.setOnClickListener(this)
        login_fragment_btn_sign_in.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.fragment_login_iv_back -> activity?.onBackPressed()
            R.id.login_fragment_tv_sign_up -> {
                val registrationFragment = RegistrationFragment()
                fragmentCallbacks?.addFragment(registrationFragment, registrationFragment.getSimpleTAG(), resources.getString(R.string.SLIDE_LEFT))
            }
            R.id.login_fragment_btn_sign_in -> {
                val mUserName = fragment_login_tet_username.text.toString()
                val mPassword = fragment_login_tet_password.text.toString()

                if (NetworkUtils.isConnected(activity?.applicationContext!!)) {
                    if (isValidated(mUserName, mPassword)) {
                        val memberRequesModel = MemberRequestModel()
                        memberRequesModel.username = mUserName
                        memberRequesModel.password = mPassword

                        loginViewModel.memberLogin(memberRequesModel)
                    }
                }
            }
        }
    }

    private fun isValidated(mUserName: String, mPassword: String): Boolean {
        if (mUserName.isEmpty() || mPassword.isEmpty()) {
            if (mUserName.isEmpty()) {
                fragment_login_til_username.isErrorEnabled = true
                fragment_login_til_username.error = resources.getString(R.string.error_username)
                return false
            } else {
                fragment_login_til_username.error = null
                fragment_login_til_username.isErrorEnabled = false
            }
            if (mPassword.isEmpty()) {
                fragment_login_til_password.isErrorEnabled = true
                fragment_login_til_password.error = resources.getString(R.string.error_password)
                return false
            } else {
                fragment_login_til_password.error = null
                fragment_login_til_password.isErrorEnabled = false
            }
        }
        return true
    }
}
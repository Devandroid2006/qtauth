package com.quintype.qtauth.activities

import android.os.Bundle
import com.quintype.qtauth.fragments.LoginHomeFragment
import com.quintype.qtauth.R

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

//        activity_login_iv_back?.setOnClickListener { finish() }

        val loginFragment = LoginHomeFragment().newInstance()
        addFragment(loginFragment, loginFragment.getSimpleTAG(), getString(R.string.SLIDE_LEFT))
    }
}

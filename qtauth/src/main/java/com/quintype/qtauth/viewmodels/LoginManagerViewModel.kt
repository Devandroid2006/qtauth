package com.quintype.qtauth.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.quintype.qtauth.core.LiveDataWrapper
import com.quintype.qtauth.core.LoginMainApplication
import com.quintype.qtauth.core.LoginManager
import com.quintype.qtauth.models.MemberRequestModel
import com.quintype.qtauth.models.MemberResponseModel
import com.quintype.qtauth.services.MobileAuthService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginManagerViewModel(application: Application = LoginMainApplication.mInstance!!) : BaseViewModel<LiveDataWrapper<MemberResponseModel>>(application), LoginManager {
    var mobileAuthService = MobileAuthService.getInstance()

    override fun memberLogin(memberRequestModel: MemberRequestModel) {
        mobileAuthService.memberLogin(memberRequestModel)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({ memberResponseModel ->
                Log.d("member response =", memberResponseModel.toString())
            }, {
                Log.d("member error =", it.toString())
            })
    }

    override fun memberRegistration(memberRequestModel: MemberRequestModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun memberDetail(mXQTAuth: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun memberMetadata(mXQTAuth: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun memberLogout(mXQTAuth: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loginWithFB(memberRequestModel: MemberRequestModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loginWithGmail(memberRequestModel: MemberRequestModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loginWithTwitter(memberRequestModel: MemberRequestModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loginWithLinkedIn(memberRequestModel: MemberRequestModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loginWithEmail(memberRequestModel: MemberRequestModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class Factory : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return LoginManagerViewModel() as T
        }
    }
}

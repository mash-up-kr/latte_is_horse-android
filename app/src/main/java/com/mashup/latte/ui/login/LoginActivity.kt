package com.mashup.latte.ui.login

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*
import android.content.Intent
import android.util.Log
import com.kakao.util.exception.KakaoException
import com.kakao.auth.ISessionCallback
import com.kakao.auth.Session
import com.kakao.network.ErrorResult
import com.kakao.usermgmt.UserManagement
import com.kakao.usermgmt.callback.MeV2ResponseCallback
import com.kakao.usermgmt.response.MeV2Response
import com.kakao.util.helper.log.Logger
import com.mashup.latte.data.dto.request.TokenRequest
import com.mashup.latte.data.repository.ApiRepository
import com.mashup.latte.ext.e
import com.mashup.latte.ext.plusAssign
import com.mashup.latte.ext.startActivity
import com.mashup.latte.ext.withScheduler
import com.mashup.latte.pref.UserPref
import com.mashup.latte.ui.base.BaseActivity
import com.mashup.latte.ui.main.MainActivity
import org.koin.android.ext.android.inject


class LoginActivity : BaseActivity() {

    private var callback: SessionCallback? = null
    private val userPref: UserPref by inject()
    private val repository: ApiRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.mashup.latte.R.layout.activity_login)
        init()
    }

    private fun init() {
        initView()
        initSession()
        checkLogin()
    }

    private fun initView() {
        btn_custom_login.setOnClickListener { btn_kakao_login.performClick() }
        btn_next.setOnClickListener {
            userPref.setSNSLogin(false)
            startMainActivity()
        }
    }

    private fun initSession() {
        callback = SessionCallback()
        Session.getCurrentSession().addCallback(callback)
        Session.getCurrentSession().checkAndImplicitOpen()
    }

    private fun getHashkey() {
        //hash key 얻기
//        try {
//            val info = packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNING_CERTIFICATES)
//            val signatures = info.signingInfo.apkContentsSigners
//            for (signature in signatures) {
//                val md: MessageDigest
//                md = MessageDigest.getInstance("SHA")
//                md.update(signature.toByteArray())
//                val key = String(Base64.encode(md.digest(), 0))
//                Log.d("Hash key:", "!!!!!!!$key!!!!!!")
//            }
//        } catch(e: Exception) {
//            Log.e("name not found", e.toString())
//        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onDestroy() {
        Session.getCurrentSession().removeCallback(callback)
        super.onDestroy()
    }

    private inner class SessionCallback : ISessionCallback {

        override fun onSessionOpened() {
            UserManagement.getInstance().me(object : MeV2ResponseCallback() {
                override fun onSuccess(result: MeV2Response?) {
                    val session = Session.getCurrentSession()
                    e("test", "accessToken : ${session.tokenInfo.accessToken}")
                    e("test", "refreshToken : ${session.tokenInfo.refreshToken}")
                    redirectSignupActivity(session.tokenInfo.accessToken)
                }

                override fun onSessionClosed(errorResult: ErrorResult?) {
                    Log.d("test", "onSessionClosed.")
                }
            })

        }

        override fun onSessionOpenFailed(exception: KakaoException?) {
            if (exception != null) {
                Logger.e(exception)
            }
        }
    }


    private fun checkLogin() {
        if (!userPref.isFirstLogined()) {
            if (userPref.isSNSLogin()) {
                btn_kakao_login.performClick()
            } else {
                startActivity<MainActivity>()
                finish()
            }
        }
    }

    private fun startMainActivity() {
        startActivity<MainActivity>()
        finish()
    }

    private fun redirectSignupActivity(accessToken: String) {
        disposable += repository.getLoginToken(TokenRequest(token = accessToken))
            .withScheduler()
            .subscribe({
                e(TAG, "success $it")
                if (it != null) {
                    //TODO 로그인 부분 테스트 필요
                    userPref.setSNSLogin(true)
                    userPref.setAceessToken(it.token)
                    startMainActivity()
                    finish()
                }
            }, {
                e(TAG, "error", it)
            })

    }


    companion object {
        const val TAG = "LoginActivity"
    }
}

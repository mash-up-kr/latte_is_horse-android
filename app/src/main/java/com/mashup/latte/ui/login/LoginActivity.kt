package com.mashup.latte.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*
import android.content.Intent
import com.kakao.util.exception.KakaoException
import com.kakao.auth.ISessionCallback
import com.kakao.auth.Session
import com.kakao.usermgmt.response.model.User
import com.kakao.util.helper.log.Logger
import com.mashup.latte.ext.startActivity
import com.mashup.latte.pref.UserPref
import com.mashup.latte.ui.main.MainActivity
import org.koin.android.ext.android.inject


class LoginActivity : AppCompatActivity() {

    private var callback: SessionCallback? = null
    private val userPref : UserPref by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.mashup.latte.R.layout.activity_login)
        init()
    }

    private fun init() {
        initView()
        initSession()
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
        super.onDestroy()
        Session.getCurrentSession().removeCallback(callback)
    }

    private inner class SessionCallback : ISessionCallback {

        override fun onSessionOpened() {
            redirectSignupActivity()
        }

        override fun onSessionOpenFailed(exception: KakaoException?) {
            if (exception != null) {
                Logger.e(exception)
            }
        }
    }

    private fun startMainActivity() {
        startActivity<MainActivity>()
        finish()
    }

    private fun redirectSignupActivity() {
        userPref.setSNSLogin(true)
        startMainActivity()
        finish()
    }
}

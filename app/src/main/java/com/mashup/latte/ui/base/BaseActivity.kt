package com.mashup.latte.ui.base

import androidx.appcompat.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Namget on 2020.02.27.
 */
abstract class BaseActivity : AppCompatActivity(){
    protected val disposable = CompositeDisposable()

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }
}
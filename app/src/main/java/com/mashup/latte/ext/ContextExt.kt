package com.mashup.latte.ext

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast


//Toast
fun Context.toastMakeToast(text: String, length : Int = Toast.LENGTH_SHORT) = Toast.makeText(this, text, length).show()

//Toast
fun Context.toastMakeToast(res: Int , length : Int = Toast.LENGTH_SHORT) = Toast.makeText(this, res, length).show()

//startActivity<DetailActivity>()
inline fun <reified T : Activity> Context.startActivity(bundle : Bundle? = null) {
    val intent = Intent(this, T::class.java)
    if(bundle != null) intent.putExtras(bundle)
    startActivity(intent)
}
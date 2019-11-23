package com.mashup.latte.ext

import android.content.Context
import android.widget.Toast


//Toast
fun Context.toastMakeToast(text: String, length : Int = Toast.LENGTH_SHORT) = Toast.makeText(this, text, length).show()

//Toast
fun Context.toastMakeToast(res: Int , length : Int = Toast.LENGTH_SHORT) = Toast.makeText(this, res, length).show()
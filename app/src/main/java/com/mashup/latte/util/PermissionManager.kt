package com.mashup.latte.util

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

/**
 * Created by Namget on 2020.02.05.
 */
class PermissionManager {


    companion object {
        const val REQUEST_CODE = 100

        fun checkPermission(activity: Activity, vararg permissions: String) {
            // Here, thisActivity is the current activity
            val notGrantedPermission = arrayListOf<String>()
            for (permission in permissions) {
                if (ContextCompat.checkSelfPermission(
                        activity,
                        permission
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    notGrantedPermission.add(permission)
                }
            }

            ActivityCompat.requestPermissions(
                activity,
                permissions,
                REQUEST_CODE
            )

        }
    }
}
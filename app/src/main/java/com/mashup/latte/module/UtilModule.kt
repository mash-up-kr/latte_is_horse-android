package com.mashup.latte.module

import com.mashup.latte.ui.record.CalendarManager
import com.mashup.latte.util.FileManager
import com.mashup.latte.util.NetworkManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Created by Namget on 2020.03.03.
 */
val utilModule = module {
    single {
        CalendarManager()
    }

    single {
        NetworkManager()
    }

    single {
        FileManager(androidContext())
    }

}
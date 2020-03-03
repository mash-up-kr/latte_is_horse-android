package com.mashup.latte.module

import com.mashup.latte.ui.record.CalendarManager
import org.koin.dsl.module

/**
 * Created by Namget on 2020.03.03.
 */
val utilModule = module {
    single {
        CalendarManager()
    }
}
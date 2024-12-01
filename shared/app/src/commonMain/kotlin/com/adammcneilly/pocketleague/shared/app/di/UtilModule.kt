package com.adammcneilly.pocketleague.shared.app.di

import com.adammcneilly.pocketleague.shared.app.core.currency.CurrencyFormatter
import com.adammcneilly.pocketleague.shared.app.core.currency.currencyFormatter
import com.adammcneilly.pocketleague.shared.app.core.datetime.DateTimeFormatter
import com.adammcneilly.pocketleague.shared.app.core.datetime.SystemTimeProvider
import com.adammcneilly.pocketleague.shared.app.core.datetime.TimeProvider
import com.adammcneilly.pocketleague.shared.app.core.datetime.dateTimeFormatter
import com.adammcneilly.pocketleague.shared.app.core.locale.LocaleHelper
import com.adammcneilly.pocketleague.shared.app.core.locale.localeHelper
import org.koin.dsl.module

val utilModule = module {
    single<DateTimeFormatter> {
        dateTimeFormatter()
    }

    single<CurrencyFormatter> {
        currencyFormatter()
    }

    single<LocaleHelper> {
        localeHelper()
    }

    single<TimeProvider> {
        SystemTimeProvider
    }
}

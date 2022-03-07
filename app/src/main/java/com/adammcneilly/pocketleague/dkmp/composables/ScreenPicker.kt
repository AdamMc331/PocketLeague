package com.adammcneilly.pocketleague.dkmp.composables

import androidx.compose.runtime.Composable
import com.adammcneilly.pocketleague.shared.Navigation
import com.adammcneilly.pocketleague.shared.ScreenIdentifier
import com.adammcneilly.pocketleague.shared.Screens

/**
 * Given a [screenIdentifier], render that screen.
 */
@Composable
fun Navigation.ScreenPicker(
    screenIdentifier: ScreenIdentifier
) {

    when (screenIdentifier.screen) {
        Screens.EventSummaryList -> {
            EventSummaryListScreen(state = stateProvider.get(screenIdentifier))
        }
        Screens.EventOverview -> {
            EventOverviewScreen(state = stateProvider.get(screenIdentifier))
        }
//        CountriesList ->
//            CountriesListScreen(
//                countriesListState = stateProvider.get(screenIdentifier),
//                onListItemClick = { navigate(CountryDetail, CountryDetailParams(countryName = it)) },
//                onFavoriteIconClick = { events.selectFavorite(countryName = it) },
//            )
//
//        CountryDetail ->
//            CountryDetailScreen(
//                countryDetailState = stateProvider.get(screenIdentifier)
//            )
    }
}

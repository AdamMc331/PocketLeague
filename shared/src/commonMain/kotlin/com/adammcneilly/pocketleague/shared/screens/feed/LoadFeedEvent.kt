package com.adammcneilly.pocketleague.shared.screens.feed

import com.adammcneilly.pocketleague.shared.eventlist.GetUpcomingEventsUseCase
import com.adammcneilly.pocketleague.shared.eventlist.GetUpcomingEventsUseCaseImpl
import com.adammcneilly.pocketleague.shared.screens.Events
import kotlinx.coroutines.flow.collect

/**
 * Loads the information for the feed state.
 */
fun Events.loadFeed() = screenCoroutine {
    val upcomingEventsUseCase = GetUpcomingEventsUseCaseImpl(dataRepository.eventRepository)

    val upcomingEventsFlow = upcomingEventsUseCase.invoke()

    upcomingEventsFlow.collect { useCaseResult ->
        stateManager.updateScreen(FeedViewState::class) {
            when (useCaseResult) {
                is GetUpcomingEventsUseCase.Result.Success -> {
                    it.copy(
                        showLoading = false,
                        upcomingEvents = useCaseResult.events,
                    )
                }
                is GetUpcomingEventsUseCase.Result.Error -> {
                    it.copy(
                        showLoading = false,
                        errorMessage = useCaseResult.error.message,
                    )
                }
            }
        }
    }
}

// fun Events.selectFavorite(countryName: String) = screenCoroutine {
//    val favorites = dataRepository.getFavoriteCountriesMap(alsoToggleCountry = countryName)
//    // update state with new favorites map, after toggling the value for the specified country
//    stateManager.updateScreen(CountriesListState::class) {
//        it.copy(favoriteCountries = favorites)
//    }
// }

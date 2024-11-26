package com.adammcneilly.pocketleague.shared.app.match

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MatchDetailViewModel(
    private val matchId: String,
    private val getMatchDetailUseCase: GetMatchDetailUseCase,
    private val getGamesForMatchUseCase: GetGamesForMatchUseCase,
) : ViewModel() {
    private val mutableState = MutableStateFlow(MatchDetailUiState.placeholderState())
    val state = mutableState.asStateFlow()

    init {
        // Consider combining games and matches to a single type and requesting them together
        // with a use case.
        observeMatchDetail()
        fetchGames()
    }

    private fun observeMatchDetail() {
        val matchFlow = getMatchDetailUseCase
            .invoke(matchId)

        viewModelScope.launch {
            matchFlow.collect { match ->
                mutableState.update { currentState ->
                    currentState.copy(
                        match = match,
                    )
                }
            }
        }
    }

    private fun fetchGames() {
        viewModelScope.launch {
            val gamesList = getGamesForMatchUseCase
                .invoke(matchId)

            mutableState.update { currentState ->
                currentState.copy(
                    games = gamesList,
                )
            }
        }
    }
}

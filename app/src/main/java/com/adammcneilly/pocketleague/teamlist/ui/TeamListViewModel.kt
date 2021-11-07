package com.adammcneilly.pocketleague.teamlist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adammcneilly.pocketleague.core.models.Team
import com.adammcneilly.pocketleague.teamlist.domain.models.FetchTeamListResult
import com.adammcneilly.pocketleague.teamlist.domain.usecases.FetchAllTeamsUseCase
import com.adammcneilly.pocketleague.teamoverview.ui.toOverviewDisplayModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamListViewModel @Inject constructor(
    private val fetchAllTeamsUseCase: FetchAllTeamsUseCase,
) : ViewModel() {

    private val _viewState = MutableStateFlow(TeamListViewState())
    val viewState: StateFlow<TeamListViewState> = _viewState

    init {
        viewModelScope.launch {
            val allTeamsResult = fetchAllTeamsUseCase()

            when (allTeamsResult) {
                is FetchTeamListResult.Success -> {
                    processSuccessfulTeamListRequest(allTeamsResult)
                }
                is FetchTeamListResult.Failure -> {
                    processFailedTeamListRequest()
                }
            }
        }
    }

    private fun processFailedTeamListRequest() {
        _viewState.value = _viewState.value.copy(
            showLoading = false,
            showContent = false,
            showError = true,
        )
    }

    private fun processSuccessfulTeamListRequest(allTeamsResult: FetchTeamListResult.Success) {
        _viewState.value = _viewState.value.copy(
            showLoading = false,
            showContent = true,
            teams = allTeamsResult.teams.map(Team::toOverviewDisplayModel),
        )
    }
}

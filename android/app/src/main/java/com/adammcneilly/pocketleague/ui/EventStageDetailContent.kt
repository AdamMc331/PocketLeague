package com.adammcneilly.pocketleague.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pocketleague.shared.screens.eventstagedetail.EventStageDetailViewState
import com.adammcneilly.tournament.bracket.displaymodels.BracketDisplayModel
import com.adammcneilly.tournament.bracket.displaymodels.BracketMatchDisplayModel
import com.adammcneilly.tournament.bracket.displaymodels.BracketRoundDisplayModel
import com.adammcneilly.tournament.bracket.displaymodels.BracketTeamDisplayModel
import com.adammcneilly.tournament.bracket.ui.MultiEliminationBracket

/**
 * Renders the [viewState] for the event stage detail screen.
 */
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun EventStageDetailContent(
    viewState: EventStageDetailViewState,
    onMatchClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val brackets = viewState.getBracket()

    if (brackets != null) {
        MultiEliminationBracket(
            brackets = brackets,
            modifier = modifier,
        )
    }
//    LazyColumn(
//        modifier = modifier
//            .fillMaxSize(),
//        contentPadding = PaddingValues(16.dp),
//        verticalArrangement = Arrangement.spacedBy(16.dp),
//    ) {
//        viewState.matchesByDateDisplayModel?.matchesByDate?.forEach { (date, matchList) ->
//            item {
//                Text(
//                    text = date,
//                    style = MaterialTheme.typography.headlineSmall,
//                    modifier = Modifier
//                        .defaultMinSize(minWidth = 100.dp)
//                        .placeholderMaterial(visible = viewState.matchesByDateDisplayModel?.isPlaceholder == true),
//                )
//            }
//
//            item {
//                Card {
//                    matchList.forEachIndexed { index, match ->
//                        MatchListItem(
//                            displayModel = match,
//                            modifier = Modifier
//                                .clickable {
//                                    onMatchClicked.invoke(match.matchId)
//                                },
//                        )
//
//                        if (index != matchList.lastIndex) {
//                            Divider()
//                        }
//                    }
//                }
//            }
//        }
//    }
}

private fun EventStageDetailViewState.getBracket(): List<BracketDisplayModel>? {
    if (this.allMatches.isEmpty()) {
        return null
    }

    // Ideally I organize this data better, but let's go with the quick solution to get something to test.
    // From all matches, filter out the rounds, and order them by round number.
    // Then, group matches by their round.
    val (lowerBracketRounds1, upperBracketRounds1) = this.allMatches.groupBy { match ->
        match.round
    }
        .entries
        .partition {
            it.key.number < 0
        }

    val lowerBracketRounds = lowerBracketRounds1
        .map { (round, matches) ->
            BracketRoundDisplayModel(
                name = round.name,
                matches = matches.map { match ->
                    BracketMatchDisplayModel(
                        topTeam = BracketTeamDisplayModel(
                            name = match.orangeTeamResult.team.name,
                            score = match.orangeTeamResult.score.toString(),
                            isWinner = match.orangeTeamResult.winner,
                        ),
                        bottomTeam = BracketTeamDisplayModel(
                            name = match.blueTeamResult.team.name,
                            score = match.blueTeamResult.score.toString(),
                            isWinner = match.blueTeamResult.winner,
                        ),
                    )
                },
            )
        }

    val upperBracketRounds = upperBracketRounds1
        .map { (round, matches) ->
            BracketRoundDisplayModel(
                name = round.name,
                matches = matches.map { match ->
                    BracketMatchDisplayModel(
                        topTeam = BracketTeamDisplayModel(
                            name = match.orangeTeamResult.team.name,
                            score = match.orangeTeamResult.score.toString(),
                            isWinner = match.orangeTeamResult.winner,
                        ),
                        bottomTeam = BracketTeamDisplayModel(
                            name = match.blueTeamResult.team.name,
                            score = match.blueTeamResult.score.toString(),
                            isWinner = match.blueTeamResult.winner,
                        ),
                    )
                },
            )
        }

    return listOf(
        BracketDisplayModel("Upper Bracket", upperBracketRounds),
        BracketDisplayModel("Lower Bracket", lowerBracketRounds),
    )
}

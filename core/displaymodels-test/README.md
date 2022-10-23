# Core Display Models Test

In this module, we define some test display models to be used inside other unit tests, or UI tests that render a component.

## How To Define Test Display Models

All test display models should be defined as an extension property for the original display model, like this:

```kotlin
val MatchDetailDisplayModel.Companion.blueWinner: MatchDetailDisplayModel
    get() = MatchDetailDisplayModel(
        matchId = "matchId",
        localDate = "Jan 01, 2000",
        localTime = "12:00",
        eventName = "RLCS World Championship",
        stageName = "Playoffs",
        relativeDateTime = "1d ago",
        orangeTeamResult = MatchTeamResultDisplayModel.winner,
        blueTeamResult = MatchTeamResultDisplayModel.loser,
    )
```

This way, our call sites can look something like this:

```kotlin
@Test
fun renderBlueTeamWinner() {
    paparazzi.snapshotScreen(
        useDarkTheme = useDarkTheme,
    ) {
        MatchCard(match = MatchDetailDisplayModel.blueWinner)
    }
}
```

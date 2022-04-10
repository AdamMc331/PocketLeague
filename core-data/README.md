# Core-Data Module

The core-data module manages any classes that are shared among different data layer implementations throughout the Pocket League application. This is classes such as [DataResult](src/commonMain/kotlin/com/adammcneilly/pocketleague/core/data/DataResult.kt), which helps determine if a data request was successful or not by wrapping both cases into a single type. 
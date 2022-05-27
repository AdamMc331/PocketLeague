package com.adammcneilly.pocketleague.core.models

/**
 * Represents a person who can compete in an RLCS event.
 */
data class Player(
    val id: String = "",
    val slug: String = "",
    val tag: String = "",
    val country: String = "",
)

// {
//            "player": {
//              "_id": "5f3d8fdd95f40596eae23f61",
//              "slug": "3f61-freeway",
//              "tag": "freeway",
//              "country": "ar"
//            },
//            "stats": {
//              "core": {
//                "shots": 1,
//                "goals": 0,
//                "saves": 1,
//                "assists": 0,
//                "score": 145,
//                "shootingPercentage": 0
//              }
//            },
//            "advanced": {
//              "goalParticipation": 0,
//              "rating": 0.19825662249663228
//            }
//          },

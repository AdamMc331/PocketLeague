package com.adammcneilly.pocketleague.data.event

import com.apollographql.apollo3.ApolloClient

val startGGApolloClient = ApolloClient
    .Builder()
    .addHttpHeader("Authorization", "Bearer 24a270722c5612eef2ddf509817672ce")
    .serverUrl("https://api.start.gg/gql/alpha")
    .build()

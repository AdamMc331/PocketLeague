package com.adammcneilly.pocketleague.data.startgg

import com.apollographql.apollo3.ApolloClient

val StartGGApolloClient = ApolloClient
    .Builder()
    .addHttpHeader("Authorization", "Bearer ${BuildKonfig.START_GG_API_KEY}")
    .serverUrl("https://api.start.gg/gql/alpha")
    .build()

package com.adammcneilly.pocketleague.event.implementation.smashgg

import com.apollographql.apollo3.ApolloClient

/**
 * A shared instance of an [ApolloClient] to be used for smash.gg API requests.
 */
val smashGGApolloClient = ApolloClient.Builder()
    .serverUrl("https://api.smash.gg/gql/alpha")
    .addHttpInterceptor(SmashGGAuthorizationInterceptor())
    .build()

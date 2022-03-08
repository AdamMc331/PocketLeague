package com.adammcneilly.pocketleague.shared.datalayer.sources._graphql

import com.apollographql.apollo3.ApolloClient

class SmashGGApolloClient {
    internal val apolloClient = ApolloClient.Builder()
        .serverUrl("https://api.smash.gg/gql/alpha")
        .addHttpInterceptor(SmashGGAuthorizationInterceptor())
        .build()
}

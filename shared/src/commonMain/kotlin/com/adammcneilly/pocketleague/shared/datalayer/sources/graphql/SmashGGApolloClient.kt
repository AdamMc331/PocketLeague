package com.adammcneilly.pocketleague.shared.datalayer.sources.graphql

import com.adammcneilly.pocketleague.core.data.Result
import com.adammcneilly.pocketleague.shared.debugLogger
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class SmashGGApolloClient {
    private val apolloClient = ApolloClient.Builder()
        .serverUrl("https://api.smash.gg/gql/alpha")
        .addHttpInterceptor(SmashGGAuthorizationInterceptor())
        .build()

    /**
     * A helper function that will call the supplied [query] to our [apolloClient], and observe it as a Flow.
     *
     * Will return a flow where the [QueryData] is mapped to a [UIModel] via the [transform] lambda.
     */
    fun <QueryData : Query.Data, UIModel> observeQuery(
        query: Query<QueryData>,
        transform: (QueryData) -> UIModel,
    ): Flow<Result<UIModel>> {
        return apolloClient
            .query(query)
            .toFlow()
            .map { queryResponse ->
                val responseData = queryResponse.data

                @Suppress("TooGenericExceptionThrown")
                if (responseData != null) {
                    val mappedData = transform.invoke(responseData)
                    Result.Success(mappedData) as Result<UIModel>
                } else {
                    throw Throwable("Null response data for query: $query")
                }
            }
            .catch { error ->
                debugLogger.log("Query ${query.name()} failed. Error: $error")

                emit(Result.Error(error))
            }
    }
}

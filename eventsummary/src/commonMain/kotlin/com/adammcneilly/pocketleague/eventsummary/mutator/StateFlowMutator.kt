package com.adammcneilly.pocketleague.eventsummary.mutator

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * Defines a [Mutator] to convert a [Flow] of [Action] into a [StateFlow] of [State].
 *
 * [scope]: The [CoroutineScope] for the resulting [StateFlow]. Any [Action]s sent if there are no
 * subscribers to the output [StateFlow] will suspend until there is as least one subscriber.
 *
 * [initialState]: The seed state for the resulting [StateFlow].
 *
 * [started]: Semantics for the "hotness" of the output [StateFlow] @see [Flow.stateIn]
 *
 * [stateTransform]: Further transformations o be applied to the output [StateFlow]
 *
 * [actionTransform]: Defines the transformations to the [Action] [Flow] to create [Mutation]s
 * of state that will be reduced into the [initialState]. This is often achieved through the
 * [toMutationStream] [Flow] extension function.
 */
fun <Action : Any, State : Any> stateFlowMutator(
    scope: CoroutineScope,
    initialState: State,
    started: SharingStarted = SharingStarted.WhileSubscribed(DefaultStopTimeoutMillis),
    stateTransform: (Flow<State>) -> Flow<State> = { it },
    actionTransform: (Flow<Action>) -> Flow<Mutation<State>>
): Mutator<Action, StateFlow<State>> = object : Mutator<Action, StateFlow<State>> {
    var seed = initialState
    val actions = MutableSharedFlow<Action>()

    override val state: StateFlow<State> =
        stateTransform(
            flow {
                // Seed the reduction with the last produced state
                emitAll(
                    actionTransform(actions)
                        .reduceInto(seed)
                        .onEach(::seed::set)
                )
            }
        )
            .stateIn(
                scope = scope,
                started = started,
                initialValue = initialState
            )

    override val accept: (Action) -> Unit = { action ->
        scope.launch {
            // Suspend till downstream is connected
            actions.subscriptionCount.first { it > 0 }
            actions.emit(action)
        }
    }
}

/**
 * Represents a type as a StateFlowMutator of itself with no op [Action]s.
 *
 * This is typically useful for testing or previews
 */
fun <Action : Any, State : Any> State.asNoOpStateFlowMutator(): Mutator<Action, StateFlow<State>> =
    object : Mutator<Action, StateFlow<State>> {
        override val accept: (Action) -> Unit = {}
        override val state: StateFlow<State> = MutableStateFlow(this@asNoOpStateFlowMutator)
    }

@Suppress("TopLevelPropertyNaming")
private const val DefaultStopTimeoutMillis = 5000L

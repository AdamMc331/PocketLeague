package com.adammcneilly.pocketleague.eventsummary.mutator

interface Mutator<Action : Any, State : Any> {
    val state: State
    val accept: (Action) -> Unit
}

/**
 * Data class holding a change transform for a type [T].
 */
data class Mutation<T : Any>(
    val mutate: T.() -> T
)

fun <State : Any> Mutator<Mutation<State>, *>.accept(
    mutator: State.() -> State
) = accept(Mutation(mutator))

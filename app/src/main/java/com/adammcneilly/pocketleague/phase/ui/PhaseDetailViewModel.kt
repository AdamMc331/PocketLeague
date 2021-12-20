package com.adammcneilly.pocketleague.phase.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * A state management container for the [PhaseDetailScreen].
 */
@HiltViewModel
class PhaseDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel()

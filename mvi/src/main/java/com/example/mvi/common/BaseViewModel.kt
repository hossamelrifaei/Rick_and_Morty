package com.example.mvi.common

import androidx.lifecycle.ViewModel

abstract class BaseViewModel<EVENT, STATE, SIDE_EFFECT>(
     val factory: IntentFactory<EVENT, STATE, SIDE_EFFECT>
) : ViewModel()
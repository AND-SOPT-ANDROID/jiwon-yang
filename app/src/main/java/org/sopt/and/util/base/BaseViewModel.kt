package org.sopt.and.util.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

open abstract class BaseViewModel<State : UiState, Event : UiEvent, SideEffect : UiSideEffect> : ViewModel() {

    abstract fun createInitialState(): State

    private val _uiState = MutableStateFlow(createInitialState())
    val uiState: StateFlow<State> get() = _uiState.asStateFlow()
    val currentState: State get() = _uiState.value

    private val _sideEffect = MutableSharedFlow<SideEffect>()
    val sideEffect: SharedFlow<SideEffect> get() = _sideEffect.asSharedFlow()

    protected abstract suspend fun handleEvent(event: Event)

    protected fun setState(reduce: State.() -> State) {
        _uiState.value = _uiState.value.reduce()
    }

    fun setEvent(event: Event) {
        viewModelScope.launch { handleEvent(event) }
    }

    protected fun sendSideEffect(effect: SideEffect) {
        viewModelScope.launch { _sideEffect.emit(effect) }
    }
}


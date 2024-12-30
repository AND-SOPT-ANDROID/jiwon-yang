package org.sopt.and.util.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

open abstract class BaseViewModel<State : UiState, Event : UiEvent, SideEffect : UiSideEffect> : ViewModel() {

    private val initialState: State by lazy { createInitialState() }
    abstract fun createInitialState(): State

    private val _uiState = MutableStateFlow<State>(initialState)
    val uiState: StateFlow<State> get() = _uiState.asStateFlow()
    val currentState: State get() = _uiState.value

    private val _event: MutableSharedFlow<Event> = MutableSharedFlow()
    val event: SharedFlow<Event> get() = _event.asSharedFlow()

    private val _sideEffect : MutableSharedFlow<SideEffect> = MutableSharedFlow()
    val sideEffect: Flow<SideEffect> get() = _sideEffect.asSharedFlow()

    //state 설정하는 부분, Event를 통해서만 State 변경 가능
    fun setState(reduce: State.() -> State) {
        _uiState.value = currentState.reduce()
    }

    //event 설정하는 부분
    open fun setEvent(event: Event) {
        dispatchEvent(event)
    }
    private fun dispatchEvent(event: Event) = viewModelScope.launch {
        handleEvent(event)
    }
    protected abstract suspend fun handleEvent(event: Event)

    //sideEffect 설정하는 부분
    protected fun setSideEffect(effect: SideEffect) {
        viewModelScope.launch { _sideEffect.emit(effect) }
    }
}


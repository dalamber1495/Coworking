package com.issart.coworking.android.authScreen.viewModels

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.issart.coworking.android.R
import com.issart.coworking.android.authScreen.model.AuthUiEvent
import com.issart.coworking.android.authScreen.model.AuthUiState
import com.issart.coworking.android.authScreen.model.ValidationState
import com.issart.coworking.android.utils.UiText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthScreenViewModel : ViewModel() {


    private val _state = MutableStateFlow(AuthUiState())
    val state = _state.asStateFlow()

    private fun emailValidation(text: String): ValidationState {
        if (text == "")
            return ValidationState.EMPTY

        return when (Patterns.EMAIL_ADDRESS.matcher(text).matches()) {
            true -> ValidationState.SUCCESS
            false -> {
                ValidationState.ERROR
            }
        }
    }

    private fun passwordValidation(text: String): ValidationState {
        if (text == "")
            return ValidationState.EMPTY
        if (text.length < 8)
            return ValidationState.ERROR
        return ValidationState.SUCCESS

    }

    fun onEvent(event: AuthUiEvent) {

        when (event) {
            is AuthUiEvent.SetPassword -> {
                viewModelScope.launch {
                    _state.emit(
                        _state.value.copy(
                            password = event.text,
                            validationPassword = passwordValidation(event.text)
                        )
                    )
                }
            }
            is AuthUiEvent.SetEmail -> {
                viewModelScope.launch {
                    _state.emit(
                        _state.value.copy(
                            email = event.text,
                            validationEmail = emailValidation(event.text)
                        )
                    )
                }
            }
        }

    }

}
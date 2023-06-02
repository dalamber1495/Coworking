package com.issart.coworking.android.authScreen.model

data class AuthUiState(
    val email:String = "",
    val password: String = "",
    val validationPassword :ValidationState = ValidationState.EMPTY,
    val validationEmail :ValidationState = ValidationState.EMPTY

)

enum class ValidationState{
    EMPTY,
    ERROR,
    SUCCESS
}

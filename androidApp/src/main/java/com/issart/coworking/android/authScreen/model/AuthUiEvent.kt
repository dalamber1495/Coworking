package com.issart.coworking.android.authScreen.model

sealed class AuthUiEvent{
    class SetPassword(val text:String):AuthUiEvent()
    class SetEmail(val text:String):AuthUiEvent()

}

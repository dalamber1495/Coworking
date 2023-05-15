package com.issart.coworking.android.utils

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

sealed class UiText {
    data class DynamicString(val value: String) : UiText()
    class StringResource(
        @StringRes val resId: Int,
        vararg val args: Any
    ) : UiText()

    object EmptyString : UiText()
    object SuccessValidation:UiText()

    @Composable
    fun asString(): String {
        return when (this) {
            is DynamicString -> value
            is StringResource -> stringResource(id = resId, formatArgs = args)
            is EmptyString -> ""
            is SuccessValidation -> ""
        }
    }
    fun asString(context: Context): String {
        return when (this) {
            is DynamicString -> value
            is StringResource -> context.getString(resId,*args)
            is EmptyString -> ""
            is SuccessValidation -> ""
        }
    }
}

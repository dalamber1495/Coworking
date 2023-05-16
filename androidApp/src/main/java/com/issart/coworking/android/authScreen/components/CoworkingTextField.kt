package com.issart.coworking.android.authScreen.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.issart.coworking.android.MyApplicationTheme
import com.issart.coworking.android.ui.activeContentColor
import com.issart.coworking.android.ui.backgroundFieldColor
import com.issart.coworking.android.ui.inactiveContentColor
import com.issart.coworking.android.ui.textPlaceHolderTextStyle
import com.issart.coworking.android.utils.UiText

@Composable
fun CoworkingTextField(
    modifier: Modifier,
    text: String = "",
    placeHolder: String,
    visibleIcon: Boolean = false,
    validationValue: UiText = UiText.EmptyString,
    valueCallback: (String) -> Unit,
    maxLenght: Int? = null,
    leadingIcon: Int? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {


    val visibleText = remember {
        mutableStateOf(!visibleIcon)
    }

    val visualTransformation =
        if (visibleText.value) VisualTransformation.None else PasswordVisualTransformation()

    val borderColor =
        when (validationValue) {
            is UiText.EmptyString -> Color.Transparent
            is UiText.SuccessValidation -> Color.Green
            else -> Color.Red
        }
    Column(
        modifier = modifier
    ) {
        Text(
            modifier = Modifier
                .height(20.dp)
                .padding(start = 8.dp, bottom = 4.dp),
            text = placeHolder,
            style = textPlaceHolderTextStyle,
            color = if (text.isEmpty()) Color.Transparent else inactiveContentColor
        )
        Box(
            modifier = Modifier
                .shadow(elevation = 0.dp, shape = RoundedCornerShape(12.dp))
        ) {
            OutlinedTextField(
                modifier = modifier
                    .width(323.dp)
                    .height(48.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = backgroundFieldColor,
                    focusedBorderColor = borderColor,
                    unfocusedBorderColor = Color.Transparent,
                    cursorColor = activeContentColor
                ),
                value = text,
                onValueChange = {
                    if (it.length <= (maxLenght ?: 100)) valueCallback.invoke(
                        it
                    )
                },
                leadingIcon = {
                    leadingIcon?.let {
                        Icon(
                            painter = painterResource(id = it),
                            contentDescription = null,
                            tint = inactiveContentColor
                        )
                    }
                },
                textStyle = textPlaceHolderTextStyle,
                placeholder = {
                        Text(
                            modifier = Modifier.height(40.dp),
                            text = placeHolder,
                            style = textPlaceHolderTextStyle,
                            color = inactiveContentColor
                        )
                },
                shape = RoundedCornerShape(12.dp),
                singleLine = true,
                visualTransformation = visualTransformation,
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions
            )
        }

    }
}

@Preview
@Composable
fun CoworkingTextFieldPreview() {
    MyApplicationTheme() {
        CoworkingTextField(
            modifier = Modifier,
            placeHolder = "Username",
            valueCallback = {},
            visibleIcon = true,
            text = ""
        )
    }
}
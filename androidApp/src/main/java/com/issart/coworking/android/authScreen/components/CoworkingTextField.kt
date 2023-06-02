package com.issart.coworking.android.authScreen.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import com.issart.coworking.android.authScreen.model.ValidationState
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
    validationValue: ValidationState = ValidationState.EMPTY,
    valueCallback: (String) -> Unit,
    maxLenght: Int? = null,
    leadingIcon: Int? = null,
    readOnly :Boolean= false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onClick:()->Unit = {}
) {


    val visibleText = remember {
        mutableStateOf(!visibleIcon)
    }

    val borderColor =
        when (validationValue) {
            ValidationState.EMPTY -> Color.Transparent
            ValidationState.SUCCESS -> Color.Green
            ValidationState.ERROR -> Color.Red
        }
    Column(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .shadow(elevation = 0.dp, shape = RoundedCornerShape(12.dp))
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .clickable(interactionSource = remember {
                        MutableInteractionSource()
                    }, indication = null, onClick = onClick)
                    .fillMaxWidth()
                    .height(48.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = backgroundFieldColor,
                    focusedBorderColor = borderColor,
                    unfocusedBorderColor = Color.Transparent,
                    cursorColor = activeContentColor
                ),
                readOnly = readOnly,
                enabled = !readOnly,
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
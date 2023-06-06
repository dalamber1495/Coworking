package com.issart.coworking.android.tabScreens.homeScreen.components

import android.widget.NumberPicker
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import com.chargemap.compose.numberpicker.NumberPicker
import com.issart.coworking.android.tabScreens.homeScreen.searchScreen.model.SearchScreenUiEvents
import com.issart.coworking.android.ui.*

@Composable
fun NumberPickerDialog(
    onDismissRequest: () -> Unit,
    value: Int,
    onEvent: (Int) -> Unit
) {

    Dialog(onDismissRequest = onDismissRequest) {
        AndroidView(factory = { context ->
            NumberPicker(context).apply {
                setOnValueChangedListener{picker, old, new ->
                    onEvent.invoke(new)
                }
                this.maxValue = 40
                this.minValue = 1
                setBackgroundColor(backgroundFieldColor.toArgb())
                this.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
            }
        },
        update = {
            it.value = value
        })
    }
}
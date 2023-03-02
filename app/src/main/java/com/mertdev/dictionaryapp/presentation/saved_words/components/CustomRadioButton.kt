package com.mertdev.dictionaryapp.presentation.saved_words.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun CustomRadioButton(
    text: String, selected: Boolean, onSelect: () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable { onSelect() }) {
        RadioButton(
            selected = selected, onClick = onSelect
        )
        Text(text = text)
    }
}
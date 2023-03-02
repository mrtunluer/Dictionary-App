package com.mertdev.dictionaryapp.presentation.saved_words.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mertdev.dictionaryapp.app.ui.theme.SpaceSize
import com.mertdev.dictionaryapp.domain.utils.WordOrder

@Composable
fun OrderRadioButtons(
    wordOrder: WordOrder, onOrderChange: (WordOrder) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
    ) {
        CustomRadioButton(text = "Ascending",
            selected = wordOrder is WordOrder.Ascending,
            onSelect = { onOrderChange(WordOrder.Ascending) })

        Spacer(modifier = Modifier.width(SpaceSize.Large))

        CustomRadioButton(text = "Descending",
            selected = wordOrder is WordOrder.Descending,
            onSelect = { onOrderChange(WordOrder.Descending) })
    }
}
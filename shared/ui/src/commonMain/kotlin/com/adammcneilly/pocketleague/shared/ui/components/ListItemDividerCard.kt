package com.adammcneilly.pocketleague.shared.ui.components

import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * This will create a custom [Card] component that consumes a
 * list of [items], and sets them up vertically with dividers in between
 * items. The content for each item is defined by the [listItemContent] function
 * passed in.
 */
@Composable
fun <T> ListItemDividerCard(
    items: List<T>,
    modifier: Modifier = Modifier,
    listItemContent: @Composable (T) -> Unit,
) {
    Card(
        modifier = modifier,
    ) {
        items.forEachIndexed { index, item ->
            listItemContent.invoke(item)

            if (index != items.lastIndex) {
                Divider()
            }
        }
    }
}

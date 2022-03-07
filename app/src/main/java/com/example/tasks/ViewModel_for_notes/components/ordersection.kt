package com.example.tasks.ui.model.notes.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tasks.util.Note_order
import com.example.tasks.util.Ordertype

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    noteOrder: Note_order = Note_order.Date(Ordertype.Descending),
    onOrderChange: (Note_order) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Title",
                selected = noteOrder is Note_order.Title,
                onSelect = { onOrderChange(Note_order.Title(noteOrder.ordertype)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Date",
                selected = noteOrder is Note_order.Date,
                onSelect = { onOrderChange(Note_order.Date(noteOrder.ordertype)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Color",
                selected = noteOrder is Note_order.Color,
                onSelect = { onOrderChange(Note_order.Color(noteOrder.ordertype)) }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Ascending",
                selected = noteOrder.ordertype is Ordertype.Ascending,
                onSelect = {
                    onOrderChange(noteOrder.copy(Ordertype.Ascending))
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Descending",
                selected = noteOrder.ordertype is Ordertype.Descending,
                onSelect = {
                    onOrderChange(noteOrder.copy(Ordertype.Descending))
                }
            )
        }
    }
}
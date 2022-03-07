package com.example.tasks.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TaskSwitch(text: String, isChecked: Boolean, onCheckChanged: (Boolean) -> Unit) {


        val (label, switch) = createRefs()
        Surface(
            modifier = Modifier
                .fillMaxWidth())
        {
            Text(text = text)
        }

        Switch(
            checked = isChecked,
            onCheckedChange = onCheckChanged,
            colors = SwitchDefaults.colors(
                checkedThumbColor = MaterialTheme.colors.primary,
                uncheckedThumbColor = MaterialTheme.colors.onBackground.copy(0.5f),
                checkedTrackColor = MaterialTheme.colors.primary.copy(0.5f),
                uncheckedTrackColor = MaterialTheme.colors.primary.copy(0.5f)
            ),
            modifier = Modifier
                .size(36.dp, 36.dp)

        )
    }


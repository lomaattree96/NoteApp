package com.example.tasks.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.unit.dp
import com.example.tasks.R

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Taskmenu(
    menuItems: List<String>,
    menuExpandedState: Boolean,
    selectedIndex: Int,
    updateMenuExpandStatus: () -> Unit,
    onDismissMenuView: () -> Unit,
    onMenuItemClick: (Int) -> Unit,
){
    Box(
        modifier = Modifier.fillMaxWidth()
            .padding(top = 10.dp)
            .border(0.5.dp, MaterialTheme.colors.onBackground.copy(alpha =0.5f))
            .clickable{updateMenuExpandStatus()}
    ){
        val (label , iconView ) = createRefs()
        Text(menuItems[selectedIndex],
        modifier = Modifier.fillMaxWidth())


//val displayIcon = Icon(Icons.Default.ArrowDropDown, contentDescription = null)
        Icon(
            Icons.Default.ArrowDropDown,
            contentDescription =null,
            // contentDescription = stringResource(R.string.menu_icon),
            modifier = Modifier
                .size(20.dp, 20.dp),

            tint = MaterialTheme.colors.onSurface
        )

        DropdownMenu(
            expanded = menuExpandedState,
            onDismissRequest = { onDismissMenuView() },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            menuItems.forEachIndexed { index, title ->
                DropdownMenuItem(
                    onClick = {
                        onMenuItemClick(index)
                    }) {
                    Text(text = title)
                }
            }
        }
    }
}

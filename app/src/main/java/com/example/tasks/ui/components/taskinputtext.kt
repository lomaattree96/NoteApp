package com.example.tasks.ui.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TextInput(
    modifier : Modifier =Modifier,
     text :String,
    label :String,
    maxLine :Int =2,
    onTextChange: (String) -> Unit,
  onImeAction : () -> Unit = {}

){

    val keyboardController= LocalSoftwareKeyboardController
    OutlinedTextField(
        value = text,
        onValueChange =  onTextChange,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent
        ),
        maxLines = maxLine,
        label = {Text(text = label)},
        keyboardOptions =  KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions (onDone = {
            onImeAction()
            keyboardController

        }),
        modifier = modifier
    )

}
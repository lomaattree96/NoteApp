package com.example.tasks.ui.components


import android.app.DatePickerDialog
import androidx.activity.ComponentActivity
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.tasks.R
import com.example.tasks.util.formatDateToString


import java.util.*



@ExperimentalComposeUiApi
@Composable
fun Date_Picker(
    selectedDate : Date,
    onSelectedDate: (Date) ->Unit
){
    val activity = LocalContext.current as ComponentActivity
    Box(
        modifier = Modifier.fillMaxWidth()
            .padding(top = 8.dp)
            .border(0.5.dp,MaterialTheme.colors.onBackground.copy(alpha =0.5f))
            .clickable{
               showDatePicker(activity, onSelectedDate)
            }
    ){
        val (label , iconview ) = createRefs()
        Text(text = selectedDate.formatDateToString(),
        color = MaterialTheme.colors.onSurface,
        modifier = Modifier.fillMaxWidth()
            )

        Icon(Icons.Default.DateRange,
            contentDescription = null,
            modifier = Modifier.size(20.dp, 20.dp),
            tint = MaterialTheme.colors.onSurface
            //contentDescription = stringResource(R.string.date),
        )
        }

    }



fun showDatePicker(activity: ComponentActivity, onSelectedDate: (Date) -> Unit) {
    val calendar = Calendar.getInstance()
    val listener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        onSelectedDate(calendar.time)
    }

    DatePickerDialog(
        activity,
        listener,
        calendar[Calendar.YEAR],
        calendar[Calendar.MONTH],
        calendar[Calendar.DAY_OF_MONTH]
    ).show()
}



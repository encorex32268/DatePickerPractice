package lihan.chen.datepickerpractice

import android.app.DatePickerDialog
import android.icu.util.Calendar
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.convertTo
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.Date

@ExperimentalMaterial3Api
@Composable
fun MyDatePicker(
    onDateSelected : (String) -> Unit = {}
) {
    var isShowDatePicker by remember {
        mutableStateOf(false)
    }
    val datePickerState = rememberDatePickerState(
        initialDisplayMode = DisplayMode.Picker,
        yearRange = IntRange(
            start = 1921,
            endInclusive =Calendar.getInstance().get(Calendar.YEAR)
        )
    )
    val selectedDate by remember {
        mutableStateOf(
            datePickerState.selectedDateMillis?.let {
                convertMillisToDate(it)
            } ?: ""
        )
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column {
            Button(onClick = {
                isShowDatePicker = true
            }) {
                Text(
                    text = "Show Date Picker",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "$selectedDate")
        }

        if (isShowDatePicker){
            DatePickerDialog(
                onDismissRequest = {
                    isShowDatePicker = false
                },
                confirmButton = {
                    Button(onClick = {
                        isShowDatePicker = false
                    }) {
                        Text(text = "OK")
                    }
                },
                dismissButton = {
                    Button(onClick = {
                        isShowDatePicker = false
                    }) {
                        Text(text = "Cancel")
                    }
                }
            ) {
                DatePicker(
                    showModeToggle = false,
                    state = datePickerState
                )
            }
        }
    }

}

private fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy")
    return formatter.format(Date(millis))
}
@ExperimentalMaterial3Api
@Preview
@Composable
fun MyDatePickerPreview() {
    MyDatePicker(
    )

}
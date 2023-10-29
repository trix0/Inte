package com.example.intempus.ui.pages.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerLayoutType
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.intempus.R
import com.example.intempus.ui.pages.checkInScreen.Step
import java.time.LocalDateTime
import java.time.ZoneOffset

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateTimePickerDialog(
    time: LocalDateTime,
    step: Step,
    onDateChange: (Long?) -> Unit,
    onTimeChange: (Int, Int) -> Unit,
    onStepChange: (Step) -> Unit
) {

    val timePickerState = rememberTimePickerState(time.hour, time.minute, true)
    val datePickerState = rememberDatePickerState(
        initialDisplayMode = DisplayMode.Picker, initialSelectedDateMillis = time.toInstant(
            ZoneOffset.UTC
        ).toEpochMilli()
    )
    DatePickerDialog(
        onDismissRequest = { onStepChange(Step.SHOW) },
        confirmButton = {
            when (step) {
                Step.SELECT_DATE -> {
                    TextButton(
                        onClick = {
                            onDateChange(datePickerState.selectedDateMillis)
                        }
                    ) {
                        Text(stringResource(id = R.string.next))
                    }
                }

                Step.SELECT_TIME -> TextButton(
                    onClick = {
                        onTimeChange(timePickerState.hour, timePickerState.minute)
                    }
                ) {
                    Text(stringResource(id = R.string.finish))
                }

                else -> {}
            }
        },
        dismissButton = {
            when (step) {
                Step.SELECT_DATE -> {
                    TextButton(
                        onClick = {
                            onStepChange(Step.SHOW)
                        }
                    ) {
                        Text(stringResource(id = R.string.cancel))
                    }
                }

                Step.SELECT_TIME -> TextButton(
                    onClick = {
                        onStepChange(Step.SELECT_DATE)
                    }
                ) {
                    Text(stringResource(id = R.string.back))
                }

                else -> {}
            }
        }

    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            when (step) {
                Step.SELECT_TIME -> TimePicker(

                    state = timePickerState,
                    layoutType = TimePickerLayoutType.Vertical
                )

                Step.SELECT_DATE -> DatePicker(state = datePickerState)
                else -> {}
            }
        }
    }
}
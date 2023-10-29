package com.example.intempus.ui.pages.checkInScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerLayoutType
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.intempus.R
import com.example.intempus.ui.pages.components.DateTimePickerDialog
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

@Composable
fun CheckInScreen(
    state: CheckInScreenState,
    onTimeChange: (Int, Int) -> Unit,
    onDateChange: (Long?) -> Unit,
    onStepChange: (Step) -> Unit,
    onCheckIn: () -> Unit
) {
    val currentStep = state.currentStep;
    val checkInTime = state.checkInTime

    val formatterTime = DateTimeFormatter.ofPattern("HH:mm")
    val formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val time = checkInTime.format(formatterTime)
    val date = checkInTime.format(formatterDate)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.check_in),
            style = MaterialTheme.typography.headlineLarge
        )
        Divider(
            modifier = Modifier.height(6.dp),
            color = Color.Transparent
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            when (currentStep) {
                Step.SHOW -> CheckInDisplay(time,date)
                Step.LOADING -> Text(stringResource(id = R.string.loading))
                Step.SELECT_TIME, Step.SELECT_DATE -> DateTimePickerDialog(
                    time = checkInTime,
                    step = state.currentStep,
                    onDateChange = onDateChange,
                    onTimeChange = onTimeChange,
                    onStepChange = onStepChange
                )
            }
            Divider(
                modifier = Modifier.height(6.dp),
                color = Color.Transparent
            )
            if (state.error != ErrorType.NONE) {
                Text(
                    text = stringResource(id = R.string.check_in_time_future_error),
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Red
                )
            }
            Divider(
                modifier = Modifier.height(6.dp),
                color = Color.Transparent
            )
            ElevatedButton(
                colors = ButtonDefaults.buttonColors(),
                shape = ButtonDefaults.elevatedShape,
                modifier = Modifier.widthIn(min = 200.dp, max = 300.dp),
                onClick = { onStepChange(Step.SELECT_DATE) },
            ) {
                Text(
                    text = stringResource(id = R.string.change_time),
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
            Divider(
                modifier = Modifier.height(6.dp),
                color = Color.Transparent
            )
            ElevatedButton(
                enabled = state.error == ErrorType.NONE,
                colors = ButtonDefaults.buttonColors(),
                shape = ButtonDefaults.elevatedShape,
                modifier = Modifier.widthIn(min = 200.dp, max = 300.dp),
                onClick = onCheckIn,
            ) {
                Text(
                    text = stringResource(id = R.string.check_in),
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }

    }
}


@Composable
fun CheckInDisplay(time:String,date:String) {
    val formatter = DateTimeFormatter.ofPattern("HH:mm")
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = time, style = MaterialTheme.typography.headlineMedium)
        Text(
            text = date,
            style = MaterialTheme.typography.headlineMedium
        )
    }
}
package com.example.intempus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.intempus.ui.pages.checkInScreen.CheckInScreen
import com.example.intempus.ui.pages.checkInScreen.CheckInScreenViewModel
import com.example.intempus.ui.theme.IntempusTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            IntempusTheme {
                val viewModel = hiltViewModel<CheckInScreenViewModel>()
                val state by viewModel.state.collectAsStateWithLifecycle()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CheckInScreen(
                        state = state,
                        onDateChange = viewModel::setNewDate,
                        onTimeChange = viewModel::setNewTime,
                        onStepChange = viewModel::onStepChange,
                        onCheckIn = viewModel::onCheckIn
                    )
                }
            }
        }
    }
}

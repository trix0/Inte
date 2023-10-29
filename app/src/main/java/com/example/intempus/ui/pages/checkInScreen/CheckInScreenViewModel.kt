package com.example.intempus.ui.pages.checkInScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.intempus.domain.use_case.GetCheckInTimeUseCase
import com.example.intempus.domain.use_case.InsertCheckInTimeToDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import javax.inject.Inject

@HiltViewModel
class CheckInScreenViewModel @Inject constructor(
    private val getCheckInTimeUseCase: GetCheckInTimeUseCase,
    private val insertCheckInTimeToDatabase: InsertCheckInTimeToDatabase
) : ViewModel() {

    private val _state: MutableStateFlow<CheckInScreenState> = MutableStateFlow(
        CheckInScreenState(
            checkInTime = LocalDateTime.now(),
            currentStep = Step.LOADING,
            newCheckInTime = LocalDateTime.now()
        )
    )

    val state = _state.asStateFlow()

    init {
        getCheckInTime()
    }

    private fun getCheckInTime() {
        viewModelScope.launch(Dispatchers.IO) {
            val time = getCheckInTimeUseCase()
            if (time != null) {
                _state.value = _state.value.copy(
                    checkInTime = time,
                    newCheckInTime = time,
                    currentStep = Step.SHOW
                )
            }
        }

    }

    fun onCheckIn() {
        viewModelScope.launch(Dispatchers.IO) {
            insertCheckInTimeToDatabase(_state.value.checkInTime)
        }
    }

    fun onStepChange(step: Step) {
        _state.value = _state.value.copy(currentStep = step)
    }

    fun setNewDate(mili: Long?) {
        if (mili == null) {
            return
        }
        val instant = Instant.ofEpochMilli(mili)
        val zoneId = ZoneId.systemDefault()
        val localDateTime = LocalDateTime.ofInstant(instant, zoneId)
        _state.value =
            _state.value.copy(newCheckInTime = localDateTime, currentStep = Step.SELECT_TIME)
    }

    fun setNewTime(hours: Int, minutes: Int) {
        val newTime = _state.value.newCheckInTime.withHour(hours).withMinute(minutes)
        val currentTime = LocalDateTime.now()
        if (newTime.isAfter(currentTime)) {
            _state.value = _state.value.copy(
                checkInTime = _state.value.newCheckInTime.withHour(hours).withMinute(minutes),
                newCheckInTime = _state.value.newCheckInTime.withHour(hours).withMinute(minutes),
                currentStep = Step.SHOW,
                error = ErrorType.TIME_IN_FUTURE
            )
            return
        }
        _state.value = _state.value.copy(
            checkInTime = _state.value.newCheckInTime.withHour(hours).withMinute(minutes),
            newCheckInTime = _state.value.newCheckInTime.withHour(hours).withMinute(minutes),
            currentStep = Step.SHOW,
            error = ErrorType.NONE
        )

    }



}

data class CheckInScreenState(
    val checkInTime: LocalDateTime,
    val currentStep: Step,
    val newCheckInTime: LocalDateTime,
    val error: ErrorType = ErrorType.NONE
)

enum class ErrorType {
    NONE,
    TIME_IN_FUTURE
}

enum class Step {
    LOADING,
    SHOW,
    SELECT_TIME,
    SELECT_DATE
}
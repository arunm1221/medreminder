package com.example.medreminder.presentation.onboard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medreminder.domain.usecases.onboard.GetOnBoardUseCase
import com.example.medreminder.domain.usecases.onboard.SetOnBoardUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardViewModel @Inject constructor(
    private val getOnBoardUseCase: GetOnBoardUseCase,
    private val setOnBoardUseCase: SetOnBoardUseCase
): ViewModel() {

    private val _isSeen = MutableStateFlow<Boolean?>(null)
    val isSeen :StateFlow<Boolean?> = _isSeen

    init {
        loadScreen()
    }

    private fun loadScreen() {
        viewModelScope.launch {
            getOnBoardUseCase().collect {
                _isSeen.value=it
            }
        }
    }

    fun onNextClick(){
        viewModelScope.launch {
            setOnBoardUseCase()
        }
    }
}
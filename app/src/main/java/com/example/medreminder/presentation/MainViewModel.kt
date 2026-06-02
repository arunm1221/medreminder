package com.example.medreminder.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medreminder.domain.usecases.login.GetLoggedInUserUseCase
import com.example.medreminder.domain.usecases.onboard.GetOnBoardUseCase
import com.example.medreminder.ui.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    getOnBoardUseCase: GetOnBoardUseCase,
    getLoggedInUserUseCase: GetLoggedInUserUseCase
) : ViewModel() {

    // null means still loading — kept non-null so splash stays up
    val startDestination = combine(
        getOnBoardUseCase(),
        getLoggedInUserUseCase()
    ) { isOnBoardSeen, loggedInUser ->
        when {
            loggedInUser != null -> Screen.Home.route
            isOnBoardSeen -> Screen.Login.route
            else -> Screen.Onboard.route
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)
}
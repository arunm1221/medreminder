package com.example.medreminder.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medreminder.domain.usecases.login.GetLoggedInUserUseCase
import com.example.medreminder.domain.usecases.login.LogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getLoggedInUserUseCase: GetLoggedInUserUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    private val loggedInUser = getLoggedInUserUseCase()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    fun logout(onLoggedOut: () -> Unit) {
        viewModelScope.launch {
            loggedInUser.value?.let { logoutUseCase(it) }
            onLoggedOut()
        }
    }
}
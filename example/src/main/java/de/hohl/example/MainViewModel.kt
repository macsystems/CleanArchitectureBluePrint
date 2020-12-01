package de.hohl.example

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.hohl.example.rest.BackendApi
import kotlinx.coroutines.launch

class MainViewModel(private val backendApi: BackendApi) : ViewModel() {

    fun ping() {
        viewModelScope.launch {
            val ping = backendApi.ping()

        }
    }

    fun coinList() {
        viewModelScope.launch() {
            val coinsList = backendApi.coinsList()

        }
    }
}
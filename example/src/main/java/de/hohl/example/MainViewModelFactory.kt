package de.hohl.example

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class MainViewModelFactory : ViewModelProvider.Factory, KoinComponent {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(backendApi = get()) as T
    }
}
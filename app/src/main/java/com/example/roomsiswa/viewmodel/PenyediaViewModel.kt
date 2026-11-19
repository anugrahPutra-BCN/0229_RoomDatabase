package com.example.roomsiswa.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.roomsiswa.AplikasiSiswa
import com.example.roomsiswa.viewmodel.DetailViewModel
import com.example.roomsiswa.viewmodel.EntryViewModel
import com.example.roomsiswa.viewmodel.HomeViewModel

object PenyediaViewModel {
    val Factory = viewModelFactory {

        initializer {
            HomeViewModel(aplikasiSiswa().container.repositoriSiswa)
        }

        initializer {
            EntryViewModel(aplikasiSiswa().container.repositoriSiswa)
        }

        initializer {
            DetailViewModel(
                this.createSavedStateHandle(),
                aplikasiSiswa().container.repositoriSiswa
            )
        }

        initializer {
            EditViewModel(
                this.createSavedStateHandle(),
                aplikasiSiswa().container.repositoriSiswa
            )
        }
    }
}

/**
 * Fungsi ekstensi query untuk objek [Application] dan mengembalikan sebuah instance dari
 * [AplikasiSiswa].
 */
fun CreationExtras.aplikasiSiswa(): AplikasiSiswa {
    val app = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]
    return if (app is AplikasiSiswa) {
        app
    } else {
        try {
            AplikasiSiswa.instance
        } catch (e: Exception) {
            throw IllegalStateException("AplikasiSiswa not initialized", e)
        }
    }
}
package com.example.andrewgardner.chucknorrisjokemachine.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.andrewgardner.chucknorrisjokemachine.data.DcCharacter
import io.github.erikcaffrey.livedata_databinding.di.ServiceLocator

class DcCharacterViewModel : ViewModel() {

    private val dcCharacterRepository = ServiceLocator.dcCharacterRepository
    private val dcCharacterListMutableLive = MutableLiveData<List<DcCharacter>>()

    init {
        dcCharacterRepository.receiverDcCharacterList { it ->
            dcCharacterListMutableLive.postValue(it)
        }
    }

    fun getDcCharacterList() = dcCharacterListMutableLive
}

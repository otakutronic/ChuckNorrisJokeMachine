package io.github.erikcaffrey.livedata_databinding.di

import com.example.andrewgardner.chucknorrisjokemachine.data.DcCharacterDataSource
import com.example.andrewgardner.chucknorrisjokemachine.data.DcCharacterRepository

object ServiceLocator {

    private val dcCharacterDataSource: DcCharacterDataSource = DcCharacterDataSource()

    val dcCharacterRepository = DcCharacterRepository(dcCharacterDataSource)

}
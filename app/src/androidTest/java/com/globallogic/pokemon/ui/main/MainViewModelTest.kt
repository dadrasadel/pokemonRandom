package com.globallogic.pokemon.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import androidx.test.filters.MediumTest
import com.androiddevs.shoppinglisttestingyt.getOrAwaitValue
import com.globallogic.pokemon.data.repository.Repository
import com.globallogic.pokemon.utility.CustomResponse
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import kotlin.random.Random

@ExperimentalCoroutinesApi
@HiltAndroidTest
class MainViewModelTest{

    @get:Rule
    val hilt=HiltAndroidRule(this)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var repository: Repository



    @Before
    fun setup(){
        hilt.inject()
//        mainViewModel=MainViewModel(repository)
    }
    /** MainViewModel Expect valid Data when call server
    * input variable:nothing
    * output variable:resultPoker
    */
    @Test
    fun notNullResponseData()= runBlocking{
        //Given
            val id= Random.nextInt(0, 150)
        //when
            val result=repository.getPokemon(id).asLiveData().getOrAwaitValue().data
        //then
            assertNotNull(result)
    }
    /** method return success data
    * input variable:id
    * output variable:result api
    */
    @Test
    fun methodReturnSuccessData()= runBlocking {
        //Given
            val id= Random.nextInt(0, 150)
        //when
            val result=repository.getPokemon(id).asLiveData().getOrAwaitValue()
        //then
            assertEquals(result.status,CustomResponse.Status.SUCCESS)
            assertNotNull(result.data)
    }


}
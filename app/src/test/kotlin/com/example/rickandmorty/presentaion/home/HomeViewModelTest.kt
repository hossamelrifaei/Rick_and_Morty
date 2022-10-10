package com.example.rickandmorty.presentaion.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever


@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @get:Rule
    val coroutineRule = MainDispatcherRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var viewModel: HomeViewModel

    val factory: HomeViewIntentFactory = mock()


    @Before
    fun setup() {
        val homeState = HomeState(HomeState.State.INITIAL(), PagingData.empty())
        val flow = flowOf(homeState)
        whenever(factory.modelState()).thenReturn(flow)
        viewModel = HomeViewModel(factory)
    }

    /**Should close the factory*/
    @Test
    fun onClearedShouldCloseTheFactory() {
        viewModel.onCleared()
        verify(factory).close()
        verify(factory).process(HomeViewEvents.START(viewModel.viewModelScope))
        verify(factory).modelState()
        verifyNoMoreInteractions(factory)
    }

    /**Should call factory.process when the event is start*/
    @Test
    fun processWhenEventIsStartThenCallFactoryProcess() {

        viewModel.process(HomeViewEvents.START(viewModel.viewModelScope))

        verify(factory, times(2)).process(HomeViewEvents.START(viewModel.viewModelScope))
        verify(factory).modelState()
        verifyNoMoreInteractions(factory)
    }

    /**Should call factory.process when the event is retry*/
    @Test
    fun processWhenEventIsRetryThenCallFactoryProcess() {
        viewModel.process(HomeViewEvents.RETRY)

        verify(factory).process(HomeViewEvents.START(viewModel.viewModelScope))
        verify(factory).modelState()
        verify(factory).process(HomeViewEvents.RETRY)
        verifyNoMoreInteractions(factory)
    }
}
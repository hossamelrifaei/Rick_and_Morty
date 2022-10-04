package com.example.rickandmorty.presentaion.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.rickandmorty.presentaion.home.adapter.LoadingAdapter
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever


@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = MainDispatcherRule()
    val factory: HomeViewIntentFactory = mock()
    val adapter: LoadingAdapter = mock()


    /**Should retry when the state is retry*/
    @Test
    fun subscribeToStateWhenStateIsRetryThenRetry() {
        val homeState = HomeState(HomeState.State.RETRY(), null)
        whenever(factory.modelState()).thenReturn(flowOf(homeState))
        val homeViewModel = HomeViewModel(factory, adapter)


        flowOf(homeState).onEach {
            verify(adapter).doRetry()
        }.launchIn(homeViewModel.viewModelScope)

        verify(factory).modelState()
        verify(factory).process(HomeViewEvents.START)
        verify(adapter).addLoadingAdapter()
        verifyNoMoreInteractions(factory, adapter)
    }

    /**Should navigate to the next screen when the state is navigate*/
    @Test
    fun subscribeToStateWhenStateIsNavigateThenNavigateToNextScreen() {
        val homeState = HomeState(
            HomeState.State.NAVIGATE(
                Character(
                    1,
                    "",
                    "",
                    "",
                    "",
                    "",
                    null,
                    null,
                    "",
                    ArrayList(),
                    "",
                    ""
                )
            ), null
        )
        val flow = flowOf(homeState)
        whenever(factory.modelState()).thenReturn(flow)
        val homeViewModel = HomeViewModel(factory, adapter)

        flow.onEach {
            assert(it.state is HomeState.State.NAVIGATE)
        }.launchIn(homeViewModel.viewModelScope)

        verify(factory).process(HomeViewEvents.START)
        verify(factory).modelState()
        verify(adapter).addLoadingAdapter()
        verifyNoMoreInteractions(factory, adapter)
    }

    /**Should submit data when the state is idel*/
    @Test
    fun subscribeToStateWhenStateIsIdelThenSubmitData() {
        val homeState = HomeState(HomeState.State.IDEL(), flowOf(PagingData.empty()))
        val flow = flowOf(homeState)
        whenever(factory.modelState()).thenReturn(flow)
        val homeViewModel = HomeViewModel(factory, adapter)

        flowOf(homeState).onEach {
            it.paging?.collect {
                verify(adapter).doSubmitData(any())
            }

        }.launchIn(homeViewModel.viewModelScope)

        verify(factory).process(HomeViewEvents.START)
        verify(factory).modelState()
        verify(adapter).addLoadingAdapter()
        verifyNoMoreInteractions(factory, adapter)
    }
}
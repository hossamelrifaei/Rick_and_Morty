package com.example.rickandmorty.presentaion.home

import androidx.paging.PagingData
import com.example.rickandmorty.presentaion.home.adapter.CharactersPagingSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.flowOf
import model.Character
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class HomeViewIntentFactoryTest {
    lateinit var homeViewIntentFactory: HomeViewIntentFactory
    val pagingSource: CharactersPagingSource = mock()
    val modelStore: HomeModelStore = mock()


    /**Should return the state of the model*/
    @Test
    fun modelStateShouldReturnTheStateOfTheModel() {

        homeViewIntentFactory.modelState()

        verify(modelStore).modelState()
    }

    @Before
    fun setUp() {
        homeViewIntentFactory = HomeViewIntentFactory(this.modelStore, this.pagingSource)
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(modelStore, pagingSource)
    }

    /**Should call retryintent when the event is retry*/
    @Test
    fun processWhenEventIsRetryThenCallRetryIntent() {
        homeViewIntentFactory.process(HomeViewEvents.RETRY())

        verify(modelStore).process(any())
    }

    /**Should call opencharacterdeatil when the event is oncharacterselected*/
    @Test
    fun processWhenEventIsOnCharacterSelectedThenCallOpenCharacterDeatil() {
        val character = Character(1, "", "", "", "", "", null, null, "", arrayListOf(), "", "")
        val homeViewEvents = HomeViewEvents.OnCharacterSelected(character)
        homeViewIntentFactory.process(homeViewEvents)

        verify(modelStore).process(any())
    }

    /**Should call startintent when the event is start*/
    @Test
    fun processWhenEventIsStartThenCallStartIntent() {
        val scope: CoroutineScope = mock()
        val event = HomeViewEvents.LOAD(scope)
        homeViewIntentFactory.process(event)
        verify(this.modelStore).process(any())
    }
}
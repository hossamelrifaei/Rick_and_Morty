package com.example.rickandmorty.presentaion.home

import androidx.paging.PagingData
import com.example.rickandmorty.presentaion.home.adapter.CharactersPagingSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking
import model.Character
import model.LocationModel
import model.OriginModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue


class HomeViewIntentFactoryTest {
    private lateinit var homeViewIntentFactory: HomeViewIntentFactory;
    private val pagingSource: CharactersPagingSource = mock()
    private val modelStore: HomeModelStore = mock()

    // Swaps out AndroidSchedulers.mainThread() for trampoline scheduler.
    @get:Rule
    val coroutineContext = MainDispatcherRule()


    /**Should track the character name*/
    @Test
    fun openCharacterDetailShouldTrackTheCharacterName() {
        val character = model.Character(
            id = 1,
            name = "Rick Sanchez",
            status = "Alive",
            species = "Human",
            type = "",
            gender = "Male",
            origin = OriginModel(name = "Earth (C-137)", url = ""),
            location = LocationModel(name = "Earth (Replacement Dimension)", url = ""),
            image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            episode = arrayListOf("https://rickandmortyapi.com/api/episode/1"),
            url = "https://rickandmortyapi.com/api/character/1",
            created = "2017-11-04T18:48:46.250Z"
        )


        val homeViewIntentFactory = HomeViewIntentFactory(
            modelStore, pagingSource
        )

        homeViewIntentFactory.toIntent(HomeViewEvents.OnCharacterSelected(character))


    }

    /**Should navigate to the character detail screen*/
    @Test
    fun openCharacterDetailShouldNavigateToTheCharacterDetailScreen() {
        val character = Character(
            id = 1,
            name = "Rick Sanchez",
            status = "Alive",
            species = "Human",
            type = "",
            gender = "Male",
            origin = OriginModel(name = "Earth (C-137)", url = ""),
            location = LocationModel(name = "Earth (Replacement Dimension)", url = ""),
            image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            episode = arrayListOf("https://rickandmortyapi.com/api/episode/1"),
            url = "https://rickandmortyapi.com/api/character/1",
            created = "2017-11-04T18:48:46.250Z"
        )

        val intentFactory = HomeViewIntentFactory(mock(), mock())

        val intent = intentFactory.openCharacterDetail(character)

        assertNotNull(intent)
        assertTrue { intent.sideEffect is HomeState.HomeSideEffect.NAVIGATE }
        assertEquals((intent.sideEffect as HomeState.HomeSideEffect.NAVIGATE).character, character)

        val sideEffectIntent = intent

        assertNotNull(sideEffectIntent.sideEffect)
        assertTrue { sideEffectIntent.sideEffect is HomeState.HomeSideEffect.NAVIGATE }

        val navigateSideEffect = sideEffectIntent.sideEffect as HomeState.HomeSideEffect.NAVIGATE

        assertEquals(character, navigateSideEffect.character)
    }

    /**Should return an error when the request is failed*/
    @Test
    fun startIntentWhenRequestIsFailedThenReturnError() {
        val scope = CoroutineScope(coroutineContext.testDispatcher)

        homeViewIntentFactory.process(HomeViewEvents.LOAD(scope))

        verify(modelStore).process(any())

    }

    /**Should return a list of characters when the request is successful*/
    @Test
    fun startIntentWhenRequestIsSuccessfulThenReturnListOfCharacters() {
//        val pagingSource = mock<CharactersPagingSource>()
//        val modelStore = mock<HomeModelStore>()
//        val homeViewIntentFactory = HomeViewIntentFactory(modelStore, pagingSource)
//        val scope = mock<CoroutineScope>()
//        val intent = homeViewIntentFactory.startIntent(scope)
//        assertNotNull(intent)
    }

    @Before
    fun setUp() {

        homeViewIntentFactory =
            HomeViewIntentFactory(modelStore, pagingSource);
    }

    /**Should return nothingintent when the viewevent is initial*/
    @Test
    fun toIntentWhenViewEventIsInitialThenReturnNothingIntent() {
        val intent = homeViewIntentFactory.toIntent(HomeViewEvents.INITIAL);
        assertNotNull(intent);
    }

    /**Should return retryintent when the viewevent is retry*/
    @Test
    fun toIntentWhenViewEventIsRetryThenReturnRetryIntent() {
        val intent = homeViewIntentFactory.toIntent(HomeViewEvents.RETRY());
        runBlocking {
            assertEquals(
                intent.reduce(HomeState(HomeState.State.IDEL(), PagingData.empty())),
                HomeState(HomeState.State.RETRY, PagingData.empty())
            );
        }

    }

    /**Should return opencharacterdeatil intent when the viewevent is oncharacterselected*/
    @Test
    fun toIntentWhenViewEventIsOnCharacterSelectedThenReturnOpenCharacterDeatil() {
        val character =
            model.Character(1, "", "", "", "", "", null, null, "", arrayListOf(), "", "");
        val intent = homeViewIntentFactory.toIntent(HomeViewEvents.OnCharacterSelected(character));
        assertTrue(intent.sideEffect is HomeState.HomeSideEffect.NAVIGATE)
        assertEquals((intent.sideEffect as HomeState.HomeSideEffect.NAVIGATE).character, character)
    }
}
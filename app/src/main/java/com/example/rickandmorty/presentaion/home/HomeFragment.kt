package com.example.rickandmorty.presentaion.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import com.example.mvi.common.MVIView
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentHomeBinding
import com.example.rickandmorty.presentaion.home.adapter.LoadingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.BUFFERED
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import model.Character
import model.LocationModel
import model.OriginModel
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : Fragment(),
    MVIView<HomeViewEvents, HomeState, HomeState.HomeSideEffect> {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewIntentFactory by viewModels()
    private val eventChannel = Channel<HomeViewEvents>(BUFFERED)
    private val event = eventChannel.receiveAsFlow()


    @Inject
    lateinit var adapter: LoadingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.charactersList.adapter = adapter {
            eventChannel.trySend(it)
        }


        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewEvents().onEach { viewModel.process(it) }
                    .launchIn(this)
                viewModel.subscribe(
                    { this.subscribeToState(it) },
                    { this.subscribeToSideEffect(it) })
            }
        }
        eventChannel.trySend(HomeViewEvents.LOAD())

    }


    override fun onDestroyView() {
        binding.charactersList.adapter = null
        _binding = null
        super.onDestroyView()
    }



    override fun viewEvents(): Flow<HomeViewEvents> {
        val flows = listOf(
            binding.fab.clicks().map { HomeViewEvents.OnCharacterSelected(Character(
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
            )) },
            event
        )
        return flows.merge()
    }

    fun View.clicks(): Flow<Unit> = callbackFlow {
        val listener = View.OnClickListener { trySend(Unit) }
        setOnClickListener(listener)
        awaitClose {
            setOnClickListener(null)
        }
    }

    override fun CoroutineScope.subscribeToState(state: Flow<HomeState>) {
        launch {
            state.collectLatest {
                Log.d("INTENT", it.name)
                when (it.state) {
                    is HomeState.State.IDEL -> adapter.doSubmitData(it.paging)
                    is HomeState.State.RETRY -> adapter.doRetry()
                }
            }
        }
    }

    override fun CoroutineScope.subscribeToSideEffect(sideEffect: Flow<HomeState.HomeSideEffect>) {

        launch {
            sideEffect.collectLatest {
                when (it) {
                    is HomeState.HomeSideEffect.NAVIGATE -> {
                    binding.root.findNavController()
                        .navigate(
                            R.id.action_home_to_detailFragment
                        )
                    }
                }
            }
        }
    }

}
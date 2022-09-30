package com.example.rickandmorty.presentaion.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.myupvote.common.ViewEvent
import com.example.rickandmorty.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flattenMerge
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@OptIn(FlowPreview::class)
@AndroidEntryPoint
class HomeFragment : Fragment(), ViewEvent<HomeViewEvents> {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<HomeViewModel>()
    private lateinit var lifeEvents: Flow<HomeViewEvents>

    @Inject
    lateinit var adapter: CharactersAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        lifeEvents = flowOf(HomeViewEvents.OnViewStart)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.charactersList.adapter = adapter
        lifeEvents = flowOf(HomeViewEvents.OnViewReady)
        viewEvents()
            .onEach { event -> viewModel.process(event) }
            .launchIn(MainScope())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.charactersList.adapter = null
        _binding = null
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) = HomeFragment()
    }

    override fun viewEvents(): Flow<HomeViewEvents> {
        val flows = listOf(
            lifeEvents
        )


        return flows.asFlow().flattenMerge(flows.size)
    }

}
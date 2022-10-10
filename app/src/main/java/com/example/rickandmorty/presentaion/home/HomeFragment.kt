package com.example.rickandmorty.presentaion.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.mvi.common.ViewEvent
import com.example.rickandmorty.databinding.FragmentHomeBinding
import com.example.rickandmorty.presentaion.home.adapter.LoadingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : Fragment(), ViewEvent<HomeViewEvents> {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<HomeViewModel>()

    @Inject
    lateinit var adapter: LoadingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.charactersList.adapter = adapter()

        viewModel.state.observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    when (it.state) {
                        is HomeState.State.IDEL -> adapter.doSubmitData(it.paging)
                        is HomeState.State.NAVIGATE -> {}
                        is HomeState.State.RETRY -> {}
                        is HomeState.State.INITIAL -> {}
                    }
                }

            }
        }

        viewEvents()
            .onEach { event -> viewModel.process(event) }
            .launchIn(MainScope())

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.charactersList.adapter = null
        _binding = null
    }


    @OptIn(FlowPreview::class)
    override fun viewEvents(): Flow<HomeViewEvents> = listOf<Flow<HomeViewEvents>>().merge()

}
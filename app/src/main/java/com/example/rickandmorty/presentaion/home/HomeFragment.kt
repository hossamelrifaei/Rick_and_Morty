package com.example.rickandmorty.presentaion.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.rickandmorty.common.ViewEvent
import com.example.rickandmorty.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flattenConcat
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


@AndroidEntryPoint
class HomeFragment : Fragment(), ViewEvent<HomeViewEvents> {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.concatAdapter.observe(viewLifecycleOwner) {
            binding.charactersList.adapter = it
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
    override fun viewEvents(): Flow<HomeViewEvents> {
        val flows = listOf<Flow<HomeViewEvents>>()
        return flows.asFlow().flattenConcat()
    }

}
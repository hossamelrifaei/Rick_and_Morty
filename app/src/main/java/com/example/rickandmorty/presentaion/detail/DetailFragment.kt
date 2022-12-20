package com.example.rickandmorty.presentaion.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.mvi.common.MVIView
import com.example.rickandmorty.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class DetailFragment : Fragment(), MVIView<DetailViewEvents, DetailState, DetailSideEffect> {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<DetailViewModel>()
    private val args: DetailFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.process(DetailViewEvents.INITIAL())
        args.charachter
        viewEvents()
            .onEach { event -> viewModel.process(event) }
            .launchIn(MainScope())
    }

    override fun viewEvents(): Flow<DetailViewEvents> {
        val flows = listOf<Flow<DetailViewEvents>>(
        )
        return flows.merge()
    }

    override fun CoroutineScope.subscribeToState(state: Flow<DetailState>) {

    }

    override fun CoroutineScope.subscribeToSideEffect(sideEffect: Flow<DetailSideEffect>) {

    }

}
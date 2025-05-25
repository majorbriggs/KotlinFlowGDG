package com.example.kotlinflowsgdg.flows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.kotlinflowsgdg.databinding.FragmentFlowsBinding
import com.example.kotlinflowsgdg.collectWithLifecycle
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch

class FlowsFragment : Fragment() {

    private val viewModel: FlowsViewModel by viewModels()
    private lateinit var binding: FragmentFlowsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentFlowsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//
//        viewModel.stateFlowState.collectWithLifecycle(viewLifecycleOwner) {
//            binding.text1.text = "StateFlow: ${it.number}"
//        }
//
//        viewModel.sharedFlowState.collectWithLifecycle(viewLifecycleOwner) {
//            binding.text2.text = "SharedFlow: ${it.number}"
//        }

        binding.button.setOnClickListener {
            viewModel.bump()
        }

        binding.startSecondObservation.setOnClickListener {
            viewModel.sharedFlowState.collectWithLifecycle(viewLifecycleOwner) {
                binding.text3.text = "Second observation: ${it.number}"
            }

            viewModel.channel.collectWithLifecycle(viewLifecycleOwner){
                delay(1500)
                binding.text2.text="Channel event $it"
            }

            viewModel.sharedFlow.collectWithLifecycle(viewLifecycleOwner){
                delay(1500)
                binding.text1.text="SharedFlow event $it + ${viewModel.sharedFlow.replayCache}"
            }
        }
    }
}


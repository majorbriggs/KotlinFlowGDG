package com.example.kotlinflowsgdg.sequence

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.kotlinflowsgdg.databinding.FragmentSequenceBinding
import com.example.kotlinflowsgdg.collectWithLifecycle

class SequenceFragment : Fragment() {

    private val viewModel: SequenceViewModel by viewModels()
    private lateinit var binding: FragmentSequenceBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSequenceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getSelection().collectWithLifecycle(viewLifecycleOwner){
            binding.task1Button.text = it.task1.name
            binding.task2Button.text = it.task2.name
            binding.task3Button.text = it.task3.name
        }

        with(binding){
            task1Button.setOnCheckedChangeListener { _, isChecked ->
                viewModel.onTaskClicked(0, isChecked)
            }
            task2Button.setOnCheckedChangeListener { _, isChecked ->
                viewModel.onTaskClicked(1, isChecked)
            }
            task3Button.setOnCheckedChangeListener { _, isChecked ->
                viewModel.onTaskClicked(2, isChecked)
            }
        }
    }
}
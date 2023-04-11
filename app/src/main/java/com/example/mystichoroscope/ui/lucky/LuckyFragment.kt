package com.example.mystichoroscope.ui.lucky

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.mystichoroscope.databinding.FragmentLuckyBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LuckyFragment : Fragment() {

    private val viewModel by viewModels<LuckyViewModel>()
    private var _binding: FragmentLuckyBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvLuckyInfo.animate().alpha(1f).duration = 1000

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLuckyBinding.inflate(inflater, container, false)
        return binding.root
    }
}
package com.example.mystichoroscope.ui.lucky

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnStart
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.example.mystichoroscope.R
import com.example.mystichoroscope.databinding.FragmentLuckyBinding
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Exception
import kotlin.random.Random


@AndroidEntryPoint
class LuckyFragment : Fragment() {

    private val viewModel by viewModels<LuckyViewModel>()
    private var _binding: FragmentLuckyBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.viewBackContainer.viewBack.setOnClickListener {
            prepareCard()
            flipCard()
        }

    }

    private fun flipCard() {
        try {

            // Visibilidad
            binding.viewFrontContainer.viewFront.isVisible = true
            // 3D efect
            val scale = requireContext().resources.displayMetrics.density
            val cameraDist = 8000 * scale
            binding.viewFrontContainer.viewFront.cameraDistance = cameraDist
            binding.viewBackContainer.viewBack.cameraDistance = cameraDist

            // Animation FlipOut
            val flipOutAnimatorSet =
                AnimatorInflater.loadAnimator(requireContext(), R.animator.flip_out) as AnimatorSet

            flipOutAnimatorSet.setTarget(binding.viewBackContainer.viewBack)

            // Animation Recovery FlipIN
            val flipInAnimatorSet =
                AnimatorInflater.loadAnimator(requireContext(), R.animator.flip_in) as AnimatorSet

            flipInAnimatorSet.setTarget(binding.viewFrontContainer.viewFront)

            // Start Animation

            flipInAnimatorSet.doOnStart {
                binding.tvLuckyInfo.animate().alpha(1f).duration = 2000
            }

            flipOutAnimatorSet.start()
            flipInAnimatorSet.start()

            flipInAnimatorSet.doOnEnd { binding.viewBackContainer.viewBack.isVisible = false }


        } catch (e: Exception) {

        }
    }

    private fun prepareCard() {

        val image = when (Random.nextInt(0, 5)) {
            0 -> R.drawable.card_ace_pentacles
            1 -> R.drawable.card_chariot
            2 -> R.drawable.card_death
            3 -> R.drawable.card_devil
            4 -> R.drawable.card_empress
            5 -> R.drawable.card_fool
            else -> R.drawable.card_back
        }

        binding.viewFrontContainer.ivLuckyCard.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                image
            )
        )
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
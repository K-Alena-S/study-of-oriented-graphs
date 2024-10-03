package com.example.study_of_oriented_graph.interfaces

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.study_of_oriented_graph.R
import com.example.study_of_oriented_graph.databinding.FragmentFourthOneBinding

class FourthOneFragment : Fragment() {
    private var _binding: FragmentFourthOneBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFourthOneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonText = arguments?.getString("buttonText")

        // Инициализация элементов интерфейса
        binding.buttonZeidelMatrix.setOnClickListener {
            if (buttonText != null) {
                navigateToFourthOneToFifthOneFragment(buttonText)
            }
        }

        binding.buttonCirculantVector.setOnClickListener {
            if (buttonText != null) {
                navigateToFourthOneToFifthTwoFragment(buttonText)
            }
        }
    }

    private fun navigateToFourthOneToFifthOneFragment(buttonText: String) {
        val bundle = Bundle()
        bundle.putString("buttonText", buttonText)
        findNavController().navigate(R.id.action_FourthOneFragment_to_FifthOneFragment, bundle)
    }

    private fun navigateToFourthOneToFifthTwoFragment(buttonText: String) {
        val bundle = Bundle()
        bundle.putString("buttonText", buttonText)
        findNavController().navigate(R.id.action_FourthOneFragment_to_FifthTwoFragment, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

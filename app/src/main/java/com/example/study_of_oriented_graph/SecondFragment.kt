package com.example.study_of_oriented_graph

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.study_of_oriented_graph.databinding.FragmentSecondBinding


class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        for (i in 5..24) {
            val buttonId = resources.getIdentifier("button$i", "id", requireContext().packageName)
            val button = view.findViewById<Button>(buttonId)
            button?.setOnClickListener {
                val buttonText = button.text.toString()
                navigateToThirdFragment(buttonText)
            }
        }
    }

    private fun navigateToThirdFragment(buttonText: String) {
        val bundle = Bundle()
        bundle.putString("buttonText", buttonText)
        findNavController().navigate(R.id.action_SecondFragment_to_ThirdFragment, bundle)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
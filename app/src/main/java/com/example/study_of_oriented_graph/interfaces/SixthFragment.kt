package com.example.study_of_oriented_graph.interfaces

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.study_of_oriented_graph.R
import com.example.study_of_oriented_graph.databinding.FragmentSixthBinding

class SixthFragment : Fragment() {

    private var _binding: FragmentSixthBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSixthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val matrixString = arguments?.getString("matrixString").orEmpty()
        val vertices = arguments?.getString("buttonText").orEmpty()

        binding.buttonCont.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("matrixString", matrixString)
            bundle.putString("characteristics", "cont")
            bundle.putString("vertices", vertices)
            findNavController().navigate(R.id.action_SixthFragment_to_SeventhFragment, bundle)
        }

        binding.buttonAnticont.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("matrixString", matrixString)
            bundle.putString("characteristics", "anticont")
            bundle.putString("vertices", vertices)
            findNavController().navigate(R.id.action_SixthFragment_to_SeventhFragment, bundle)
        }
    }
}

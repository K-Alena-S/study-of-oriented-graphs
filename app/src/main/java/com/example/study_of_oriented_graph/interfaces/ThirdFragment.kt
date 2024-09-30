package com.example.study_of_oriented_graph.interfaces

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.study_of_oriented_graph.R
import com.example.study_of_oriented_graph.databinding.FragmentThirdBinding

class ThirdFragment : Fragment() {

    private var _binding: FragmentThirdBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonText =  arguments?.getString("buttonText")

        binding.textView.text = when (buttonText) {
            "21" -> "$buttonText вершина"
            "22" -> "$buttonText вершины"
            "23" -> "$buttonText вершины"
            "24" -> "$buttonText вершины"
            else -> "$buttonText вершин"
        }

        binding.buttonEnterMatrix.setOnClickListener {
            val fourthFragment = FourthOneFragment()
            val bundle = Bundle()
            bundle.putString("buttonText", buttonText)
            fourthFragment.arguments = bundle
            findNavController().navigate(R.id.action_ThirdFragment_to_FourthOneFragment, bundle)
        }

        binding.buttonGetClasses.setOnClickListener {
            val fourthFragment = FourthTwoFragment()
            val bundle = Bundle()
            bundle.putString("buttonText", buttonText)
            fourthFragment.arguments = bundle
            findNavController().navigate(R.id.action_ThirdFragment_to_FourthTwoFragment, bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


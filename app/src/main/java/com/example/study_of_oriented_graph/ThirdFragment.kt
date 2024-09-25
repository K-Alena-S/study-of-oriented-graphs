package com.example.study_of_oriented_graph

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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

        val buttonText = arguments?.getString("buttonText")
        binding.textView.text = "Выбрано $buttonText вершин"

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

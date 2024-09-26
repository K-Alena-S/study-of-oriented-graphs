package com.example.study_of_oriented_graph.interfaces

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.study_of_oriented_graph.databinding.FragmentFourthTwoBinding

class FourthTwoFragment : Fragment()  {

    private var _binding: FragmentFourthTwoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFourthTwoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
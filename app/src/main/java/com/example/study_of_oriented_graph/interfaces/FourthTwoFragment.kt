package com.example.study_of_oriented_graph.interfaces

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.study_of_oriented_graph.R
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

        val buttonInt = arguments?.getString("buttonText")

        // Создаем список данных для кнопок
        val buttonData = mutableListOf<Int>()
        if (buttonInt != null) {
            for (i in 1..(buttonInt.toInt()) / 2) {
                buttonData.add(i)
            }
        } else {
            for (j in 1..14) {
                buttonData.add(j)
            }
        }

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView2)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), resources.getInteger(R.integer.count))

        val adapter = CheckBoxAdapter(buttonData)
        recyclerView.adapter = adapter
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
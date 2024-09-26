package com.example.study_of_oriented_graph

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.study_of_oriented_graph.databinding.FragmentSecondBinding


class SecondFragment : Fragment() {
    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Создаем список данных для кнопок
        val buttonData = mutableListOf<Int>()
        for (i in 5..28) {
            buttonData.add(i)
        }

        // Создаем адаптер и устанавливаем его в RecyclerView
        val buttonAdapter = ButtonAdapter(buttonData) { buttonText ->
            navigateToThirdFragment(buttonText)
        }
        binding.recyclerView.adapter = buttonAdapter

        // Устанавливаем LayoutManager в зависимости от ориентации экрана
        val orientation = resources.configuration.orientation
        binding.recyclerView.layoutManager = when (orientation) {
            Configuration.ORIENTATION_PORTRAIT -> {
                GridLayoutManager(requireContext(), 4)
            }
            Configuration.ORIENTATION_LANDSCAPE -> {
                GridLayoutManager(requireContext(), 8)
            }
            else -> {
                GridLayoutManager(requireContext(), 4)
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
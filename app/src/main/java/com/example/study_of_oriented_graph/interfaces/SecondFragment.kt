package com.example.study_of_oriented_graph.interfaces

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.study_of_oriented_graph.R
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
        for (i in 5..40) {
            buttonData.add(i)
        }

        // Создаем адаптер и устанавливаем его в RecyclerView
        val buttonAdapter = ButtonAdapter(buttonData) { buttonText ->
            navigateToThirdFragment(buttonText)
        }
        binding.recyclerView.adapter = buttonAdapter

        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), resources.getInteger(R.integer.count))
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
package com.example.study_of_oriented_graph.interfaces

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.study_of_oriented_graph.R
import com.example.study_of_oriented_graph.databinding.FragmentFirstBinding
import com.example.study_of_oriented_graph.databinding.FragmentFourthOneBinding
import com.example.study_of_oriented_graph.databinding.FragmentSixthBinding

class SixthFragment : Fragment() {
    private var matrix: Array<IntArray>? = null
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

        val matrixString = arguments?.getString("matrixString")
        val buttonText = arguments?.getString("buttonText")

        parseMatrixInput(matrixString)

        binding.buttonCont.setOnClickListener {
            if (matrixString != null) {
                val bundle = Bundle()
                bundle.putString("matrixString", matrixString)
                bundle.putString("characteristics", "cont")
                bundle.putString("buttonText", buttonText)
                findNavController().navigate(R.id.action_SixthFragment_to_SeventhFragment, bundle)
            }
        }

        binding.buttonAnticont.setOnClickListener {
            if (matrixString != null) {
                val bundle = Bundle()
                bundle.putString("matrixString", matrixString)
                bundle.putString("characteristics", "anticont")
                bundle.putString("buttonText", buttonText)
                findNavController().navigate(R.id.action_SixthFragment_to_SeventhFragment, bundle)
            }
        }

        binding.buttonDinsyst.setOnClickListener {
            if (matrixString != null) {

            }
        }

        binding.buttonFragment.setOnClickListener {
            if (matrixString != null) {

            }
        }

    }

    private fun parseMatrixInput(matrixString: String?) {
        val input = matrixString?.trim()
        if (input != null) {
            if (input.isNotEmpty()) {
                val rows = input?.split("\n")
                val matrixList = mutableListOf<IntArray>()

                if (rows != null) {
                    for (row in rows) {
                        val values = row.split(",").map { it.trim().toIntOrNull() ?: 0 }
                        matrixList.add(values.toIntArray())
                    }
                }

                matrix = matrixList.toTypedArray()

                // Теперь матрица сохранена в переменной matrix
                // Здесь можно выполнить любые действия по обработке матрицы
            }
        }
    }

}

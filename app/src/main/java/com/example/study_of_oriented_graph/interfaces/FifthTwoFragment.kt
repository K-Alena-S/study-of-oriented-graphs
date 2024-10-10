package com.example.study_of_oriented_graph.interfaces

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.study_of_oriented_graph.R

class FifthTwoFragment : Fragment() {
    private lateinit var matrixInput: EditText
    private lateinit var nextButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_fifth_two, container, false)
        matrixInput = view.findViewById(R.id.matrixInput)
        nextButton = view.findViewById(R.id.nextButton)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nextButton.setOnClickListener {
            val bundle = Bundle()
            val matrix = generateMatrix(matrixInput.text.toString())
            val matrixStr = matrixToString(matrix)
            bundle.putString("matrixString", matrixStr)
            val buttonText = arguments?.getString("buttonText")
            bundle.putString("buttonText", buttonText)
            findNavController().navigate(R.id.action_FifthTwoFragment_to_SixthFragment, bundle)
        }
    }

    private fun generateMatrix(matrixString: String): Array<IntArray> {
        // Преобразование входной строки в массив чисел
        val values = matrixString.split(",").map { it.trim().toInt() }.toIntArray()

        // Инициализация двумерного массива
        val size = values.size
        val matrix = Array(size) { IntArray(size) }

        // Заполнение двумерного массива сдвигами
        for (i in 0 until size) {
            for (j in 0 until size) {
                matrix[i][j] = values[(j - i + size) % size]
            }
        }
        return matrix
    }

    private fun matrixToString(matrix: Array<IntArray>): String {
        return matrix.joinToString("\n") { row ->
            row.joinToString(", ") { it.toString() }
        }
    }

}

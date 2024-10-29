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
import com.google.android.material.snackbar.Snackbar

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

            val onestr = matrixInput.text.toString()
            val elements = onestr.split(", ").map { it.trim() }

            val buttonText = arguments?.getString("buttonText")?.toIntOrNull()

            if (elements.size == buttonText) {
                val matrix = generateMatrix(onestr)
                val matrixStr = matrixToString(matrix)

                bundle.putString("matrixString", matrixStr)

                bundle.putString("buttonText", buttonText.toString())
                findNavController().navigate(R.id.action_FifthTwoFragment_to_SixthFragment, bundle)
            }
            else {
                Snackbar.make(view, "Ошибка: строка матрицы должна быть размером $buttonText", Snackbar.LENGTH_SHORT).show()
            }
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

package com.example.study_of_oriented_graph.interfaces

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.example.study_of_oriented_graph.R

class FifthOneFragment : Fragment() {

    private lateinit var matrixInput: EditText
    private lateinit var nextButton: Button
    private var matrix: Array<IntArray>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_fifth_one, container, false)
        matrixInput = view.findViewById(R.id.matrixInput)
        nextButton = view.findViewById(R.id.nextButton)

        nextButton.setOnClickListener {
            parseMatrixInput()
        }

        return view
    }

    private fun parseMatrixInput() {
        val input = matrixInput.text.toString().trim()
        if (input.isNotEmpty()) {
            val rows = input.split("\n")
            val matrixList = mutableListOf<IntArray>()

            for (row in rows) {
                val values = row.split(",").map { it.trim().toIntOrNull() ?: 0 }
                matrixList.add(values.toIntArray())
            }

            matrix = matrixList.toTypedArray()
            // Теперь матрица сохранена в переменной matrix
            // Здесь можно выполнить любые действия по обработке матрицы
        }
    }
}

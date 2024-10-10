package com.example.study_of_oriented_graph.interfaces

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.study_of_oriented_graph.R
import com.example.study_of_oriented_graph.algorithms.ContourString

class ContourFragment : Fragment() {

    private lateinit var contourTextView: TextView
    private lateinit var matrix: Array<IntArray>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_contour, container, false)
    }

    // В методе onViewCreated
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        contourTextView = view.findViewById(R.id.textCont)

        val matrixString = arguments?.getString("matrixString").orEmpty()
        val contourBegin = arguments?.getInt("seekBar1") ?: 4 // Задать значение по умолчанию
        val contourEnd = arguments?.getInt("seekBar2") ?: 5

        val matrix = matrixToArrayInt(matrixString)

        val cs = ContourString(matrix, contourEnd)
        val contour = cs.getArray(contourBegin, contourEnd)

//        // Преобразуем IntArray в строку для отображения
        val contourString = contour.joinToString(", ")
        contourTextView.text = "от $contourBegin до $contourEnd\n $contourString"
    }

    private fun matrixToArrayInt(matrixString: String): Array<IntArray> {
        return matrixString.trim().lines().map { line ->
            line.split(", ").map { it.toInt() }.toIntArray()
        }.toTypedArray()
    }

}

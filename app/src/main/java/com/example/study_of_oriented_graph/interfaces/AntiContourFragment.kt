package com.example.study_of_oriented_graph.interfaces

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.study_of_oriented_graph.R
import com.example.study_of_oriented_graph.algorithms.AntiContours

class AntiContourFragment: Fragment() {

    private lateinit var anricontourTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_anticontour, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        anricontourTextView = view.findViewById(R.id.textAntiCont)

        val matrixString = arguments?.getString("matrixString").orEmpty()
        var contourBegin = arguments?.getInt("seekBar1") ?: 4 // Задать значение по умолчанию
        var contourEnd = arguments?.getInt("seekBar2") ?: 6

        if (contourBegin % 2 == 1) contourBegin++
        if (contourEnd % 2 == 1) contourEnd--

        val matrix = matrixToArrayInt(matrixString)

        val cs = AntiContours(matrix, contourEnd)
        val contour = cs.getAntiCont(contourBegin, contourEnd)

        // Преобразуем IntArray в строку для отображения
        val anticontourString = contour.joinToString(", ")
        anricontourTextView.text = "от $contourBegin до $contourEnd\n $anticontourString"
    }

    private fun matrixToArrayInt(matrixString: String): Array<IntArray> {
        return matrixString.trim().lines().map { line ->
            line.split(", ").map { it.toInt() }.toIntArray()
        }.toTypedArray()
    }

}
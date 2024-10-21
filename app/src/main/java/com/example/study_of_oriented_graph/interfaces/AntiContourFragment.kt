package com.example.study_of_oriented_graph.interfaces

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
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

        val tableLayout: TableLayout = view.findViewById(R.id.tableAntiCont)

        val matrixString = arguments?.getString("matrixString").orEmpty()
        var contourBegin = arguments?.getInt("seekBar1") ?: 4 // Задать значение по умолчанию
        var contourEnd = arguments?.getInt("seekBar2") ?: 6

        if (contourBegin % 2 == 1) contourBegin++
        if (contourEnd % 2 == 1) contourEnd--

        val matrix = matrixToArrayInt(matrixString)

        val cs = AntiContours(matrix, contourEnd)
        val contour = cs.getAntiCont(contourBegin, contourEnd)

        tableLayout.removeAllViews()

        // Добавить заголовки таблицы
        val headerRow = TableRow(context)
        headerRow.addView(createTextView("Длина"))
        for (i in contourBegin..contourEnd) {
            if (i % 2 == 0)
                headerRow.addView(createTextView("$i"))
        }

        tableLayout.addView(headerRow)

        val dataRow = TableRow(context)

        dataRow.addView(createTextView("Кол-во"))
        for (k in contour) {
            dataRow.addView(createTextView(k.toString()))
        }

        tableLayout.addView(dataRow)
    }

    private fun matrixToArrayInt(matrixString: String): Array<IntArray> {
        return matrixString.trim().lines().map { line ->
            line.split(", ").map { it.toInt() }.toIntArray()
        }.toTypedArray()
    }

    // Функция для создания TextView
    private fun createTextView(text: String): TextView {
        val textView = TextView(context)
        textView.text = text
        textView.setPadding(16, 16, 16, 16) // Установка отступов
        return textView
    }

}
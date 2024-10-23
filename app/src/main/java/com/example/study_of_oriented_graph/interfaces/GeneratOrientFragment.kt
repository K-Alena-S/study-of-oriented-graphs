package com.example.study_of_oriented_graph.interfaces

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.study_of_oriented_graph.R
import com.example.study_of_oriented_graph.algorithms.Circulant
import com.example.study_of_oriented_graph.algorithms.Collection
import com.example.study_of_oriented_graph.databinding.FragmentGeneratOrientBinding

class GeneratOrientFragment : Fragment() {

    var selectedItems: ArrayList<Int>? = null
    private var _binding: FragmentGeneratOrientBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGeneratOrientBinding.inflate(inflater, container, false)
        selectedItems = arguments?.getIntegerArrayList("selectedItems")

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tableLayout: TableLayout = view.findViewById(R.id.tableLayout)
        val textName: TextView = view.findViewById(R.id.text_name)

        selectedItems = arguments?.getIntegerArrayList("selectedItems")
        val buttonInt = arguments?.getInt("buttonInt")

        textName.text = "Вершин $buttonInt, дистанции $selectedItems"

        val collection = Collection()

        Circulant(buttonInt!!, collection, selectedItems!!)

        tableLayout.removeAllViews()

        // Добавить заголовки таблицы
        val headerRow = TableRow(context)
        headerRow.addView(createTextView("Кл"))
        for (i in 3..buttonInt)
        headerRow.addView(createTextView("$i"))
        tableLayout.addView(headerRow)

        // Добавление данных в таблицу
        for (i in collection.getStrList().withIndex()) {
            val dataRow = TableRow(context)
            dataRow.addView(createTextView((i.index + 1).toString()))

            val inputString = i.value
            val intArray: IntArray = inputString.split(" ") // Разделяем строку по пробелам
                .map { it.toInt() } // Преобразуем каждую строку в Int
                .toIntArray() // Преобразуем в IntArray

            for (k in intArray)
                dataRow.addView(createTextView(k.toString()))

            tableLayout.addView(dataRow)
        }

        val columnCount = buttonInt - 2
        val rowCount = tableLayout.childCount // Получаем количество строк

        // Проходим по каждому столбцу
        for (col in 1 .. columnCount) {
            var maxValue: Int = Int.MIN_VALUE
            var maxRowIndex: Int = -1

            var minValue: Int = Int.MAX_VALUE
            var minRowIndex: Int = -1

            // Проходим по строкам и находим максимальное значение в текущем столбце
            for (row in 1 until rowCount) {  // Пропускаем заголовок
                val tableRow = tableLayout.getChildAt(row) as TableRow
                val cellValue = (tableRow.getChildAt(col) as TextView).text.toString()
                    .toIntOrNull() ?: continue

                if (cellValue > maxValue) {
                    maxValue = cellValue
                    maxRowIndex = row
                }
                if (cellValue < minValue) {
                    minValue = cellValue
                    minRowIndex = row
                }
            }

            val isDarkTheme = (requireContext().resources.configuration.uiMode and
                    Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES

            // Выделение ячейки с максимальным значением
            if (maxRowIndex != -1 && maxValue != minValue) {
                fillingColor(rowCount, maxValue, tableLayout, col, 0xFFCCCB - Int.MAX_VALUE,
                    0xd8504d - Int.MAX_VALUE, isDarkTheme)
            }

            if (minRowIndex != -1 && maxValue != minValue) {
                fillingColor(rowCount, minValue, tableLayout, col, 0xADD8E6 - Int.MAX_VALUE,
                    0x5280a4 - Int.MAX_VALUE, isDarkTheme)
            }
        }

        binding.copy.setOnClickListener {
            copyTableDataToClipboard(tableLayout, requireContext())
        }
    }

    private fun copyTableDataToClipboard(tableLayout: TableLayout, context: Context) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val stringBuilder = StringBuilder()

        for (i in 0 until tableLayout.childCount) {
            val tableRow = tableLayout.getChildAt(i) as TableRow
            for (j in 0 until tableRow.childCount) {
                val textView = tableRow.getChildAt(j) as TextView
                stringBuilder.append(textView.text)
                if (j < tableRow.childCount - 1) {
                    stringBuilder.append("\t") // Используем табуляцию для разделения ячеек
                }
            }
            stringBuilder.append("\n") // Переход на новую строку после каждой строки таблицы
        }

        // Копируем данные в буфер обмена
        val clipData = ClipData.newPlainText("Table Data", stringBuilder.toString())
        clipboard.setPrimaryClip(clipData)

        // Уведомление о том, что данные скопированы
        Toast.makeText(context, "Данные таблицы скопированы в буфер обмена", Toast.LENGTH_SHORT).show()
    }

    private fun fillingColor(rowCount: Int, Value: Int, tableLayout: TableLayout, col: Int,
                             color: Int, colorDark: Int, isDarkTheme: Boolean) {
        for (row in 1 until rowCount) {
            val tableRow = tableLayout.getChildAt(row) as TableRow
            val cellValue = (tableRow.getChildAt(col) as TextView).text.toString()
                .toIntOrNull() ?: continue
            if (cellValue == Value) {
                val maxRow = tableLayout.getChildAt(row) as TableRow
                val maxCell = maxRow.getChildAt(col) as TextView

                val lightColor = if (isDarkTheme) {
                    colorDark
                } else {
                    color
                }
                maxCell.setBackgroundColor(lightColor)
            }
        }
    }

    // Функция для создания TextView
    private fun createTextView(text: String): TextView {
        val textView = TextView(context)
        textView.text = text
        textView.setPadding(16, 16, 16, 16) // Установка отступов
        textView.setGravity(Gravity.CENTER)
        return textView
    }

}

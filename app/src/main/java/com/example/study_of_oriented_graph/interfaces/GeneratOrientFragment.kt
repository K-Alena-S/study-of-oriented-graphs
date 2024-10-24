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
import com.example.study_of_oriented_graph.algorithms.AntiContours
import com.example.study_of_oriented_graph.algorithms.Circulant
import com.example.study_of_oriented_graph.algorithms.Collection
import com.example.study_of_oriented_graph.databinding.FragmentGeneratOrientBinding

class GeneratOrientFragment : Fragment() {

    var selectedItems: ArrayList<Int>? = null
    private var _binding: FragmentGeneratOrientBinding? = null
    private val binding get() = _binding!!
    private var isAddAnticont: Boolean = false

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

        var antiCon: MutableList<IntArray> = mutableListOf()
        addingTable(tableLayout, buttonInt, collection, antiCon)

        binding.addAnticont.setOnClickListener {
            antiCon.clear()
            isAddAnticont = true
            for (i in 0..collection.getList().size - 1) {
                val cs = AntiContours(collection.getList().get(i), buttonInt)
                val anticontour = cs.getAntiContAll()
                antiCon.add(anticontour)

            }
            addingTable(tableLayout, buttonInt, collection, antiCon)
            isAddAnticont = false
        }

        binding.copy.setOnClickListener {
            copyTableDataToClipboard(tableLayout, requireContext())
        }
    }

    private fun addingTable(tableLayout: TableLayout, buttonInt: Int, col: Collection,
                            antiCon: MutableList<IntArray>) {
        tableLayout.removeAllViews()

        // Добавить заголовки таблицы
        val headerRow = TableRow(context)
        headerRow.addView(createTextView("Кл"))
        for (i in 3..buttonInt)
            headerRow.addView(createTextView("$i"))
        tableLayout.addView(headerRow)

        var count = 0

        // Добавление данных в таблицу
        for (i in col.getStrList().withIndex()) {
            val dataRow = TableRow(context)
            dataRow.addView(createTextView((i.index + 1).toString()))

            val inputString = i.value
            val intArray: IntArray = inputString.split(" ") // Разделяем строку
                .map { it.toInt() } // Преобразуем каждую строку в Int
                .toIntArray() // Преобразуем в IntArray

            for (k in intArray)
                dataRow.addView(createTextView(k.toString()))

            tableLayout.addView(dataRow)

            if (isAddAnticont) {
                val dataRowA = TableRow(context)
                dataRowA.addView(createTextView((i.index + 1).toString()))
                for (m in antiCon.get(count))
                    dataRowA.addView(createTextView(m.toString()))
                tableLayout.addView(dataRowA)
                count++
            }
        }

        val columnCount = buttonInt - 2
        val rowCount = tableLayout.childCount // Получаем количество строк

        // Проходим по каждому столбцу
        for (col in 1 .. columnCount) {
            var maxValue: Int = Int.MIN_VALUE
            var maxRowIndex: Int = -1

            var minValue: Int = Int.MAX_VALUE
            var minRowIndex: Int = -1

            var maxValueAnti: Int = Int.MIN_VALUE
            var minValueAnti: Int = Int.MAX_VALUE

            // Проходим по строкам и находим максимальное значение в текущем столбце
            for (row in 1 until rowCount) {  // Пропускаем заголовок
                if (row % 2 == 1 || !isAddAnticont) {
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
                } else {
                    val tableRow = tableLayout.getChildAt(row) as TableRow
                    val cellValue = (tableRow.getChildAt(col) as TextView).text.toString()
                        .toIntOrNull() ?: continue

                    if (cellValue > maxValueAnti) {
                        maxValueAnti = cellValue
                    }
                    if (cellValue < minValueAnti) {
                        minValueAnti = cellValue
                    }
                }
            }

            val isDarkTheme = (requireContext().resources.configuration.uiMode and
                    Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES

            // Выделение ячейки с максимальным значением
            if (maxRowIndex != -1 && maxValue != minValue) {
                fillingColor(rowCount, maxValue, tableLayout, col, 0xFFCCCB - Int.MAX_VALUE,
                    0xd8504d - Int.MAX_VALUE, isDarkTheme, true)
            }

            if (minRowIndex != -1 && maxValue != minValue) {
                fillingColor(rowCount, minValue, tableLayout, col, 0xADD8E6 - Int.MAX_VALUE,
                    0x5280a4 - Int.MAX_VALUE, isDarkTheme, true)
            }

            if (isAddAnticont) {
                if (maxValueAnti != minValueAnti) {
                    fillingColor(
                        rowCount, maxValueAnti, tableLayout, col, 0xa9e66b - Int.MAX_VALUE,
                        0x95e049 - Int.MAX_VALUE, isDarkTheme, false
                    )
                }

                if (maxValueAnti != minValueAnti) {
                    fillingColor(
                        rowCount, minValueAnti, tableLayout, col, 0xffc585 - Int.MAX_VALUE,
                        0xff9a2a - Int.MAX_VALUE, isDarkTheme, false
                    )
                }
            }
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
                             color: Int, colorDark: Int, isDarkTheme: Boolean, isCont: Boolean) {
        for (row in 1 until rowCount) {
            if (isAddAnticont && isCont && row % 2 == 0) continue // Пропускаем чётные
            if (isAddAnticont && !isCont && row % 2 != 0) continue // Пропускаем нечётные
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

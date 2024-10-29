package com.example.study_of_oriented_graph.interfaces

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.example.study_of_oriented_graph.R
import com.google.android.material.snackbar.Snackbar

class FifthOneFragment : Fragment() {

    private lateinit var matrixInput: EditText
    private lateinit var nextButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_fifth_one, container, false)
        matrixInput = view.findViewById(R.id.matrixInput)
        nextButton = view.findViewById(R.id.nextButton)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nextButton.setOnClickListener {
            val inputText = matrixInput.text.toString()
            val buttonText = arguments?.getString("buttonText")?.toIntOrNull()

            if (buttonText != null) {
                val rows = inputText.split("\n")
                var isValid = true

                // Проверка каждой строки на корректность
                for (row in rows) {
                    val elements = row.split(", ").map { it.trim() }
                    if (elements.size != buttonText) {
                        isValid = false
                        break
                    }
                }

                if (isValid && rows.size == buttonText) {
                    val bundle = Bundle()
                    bundle.putString("matrixString", inputText)
                    bundle.putString("buttonText", buttonText.toString())
                    findNavController().navigate(R.id.action_FifthOneFragment_to_SixthFragment, bundle)
                } else {
                    Snackbar.make(view, "Ошибка: матрица должна быть размером $buttonText x $buttonText", Snackbar.LENGTH_SHORT).show()
                }
            } else {
                Snackbar.make(view, "Ошибка: некорректное значение buttonText", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}

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
    private var matrixString: String? = null

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
            bundle.putString("matrixString", matrixInput.text.toString())
            val buttonText = arguments?.getString("buttonText")
            bundle.putString("buttonText", buttonText)
            findNavController().navigate(R.id.action_FifthTwoFragment_to_SixthFragment, bundle)
        }
    }

    private fun createMatrix(input: String): String {
        val numbers = input.split(",").map { it.trim().toInt() }
        val size = numbers.size
        val result = Array(size) { IntArray(size) }

        matrixString = ""
        for (i in 0 until size) {

            var str = ""
            for (j in 0 until size) {
                result[i][j] = numbers[(j + i) % size]
                str += (result[i][j].toString() + ", ")
            }
            matrixString += str + "\n"
        }

        return matrixString as String
    }
}

package com.example.study_of_oriented_graph.interfaces

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.study_of_oriented_graph.R

class FifthTwoFragment : Fragment()  {

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

        }

        return view
    }
}
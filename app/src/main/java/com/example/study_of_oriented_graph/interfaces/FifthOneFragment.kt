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
            val bundle = Bundle()
            bundle.putString("matrixString", matrixInput.text.toString())
            val buttonText = arguments?.getString("buttonText")
            bundle.putString("buttonText", buttonText)
            findNavController().navigate(R.id.action_FifthOneFragment_to_SixthFragment, bundle)
        }
    }
}

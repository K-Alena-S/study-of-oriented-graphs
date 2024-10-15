package com.example.study_of_oriented_graph.interfaces

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.study_of_oriented_graph.R
import com.example.study_of_oriented_graph.algorithms.Circulant
import com.example.study_of_oriented_graph.algorithms.Collection

class GeneratOrientFragment : Fragment() {

    var selectedItems: ArrayList<Int>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_generat_orient, container, false)
        selectedItems = arguments?.getIntegerArrayList("selectedItems")

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textView: TextView = view.findViewById(R.id.text_view_orient)

//        textView.text = selectedItems?.joinToString(", ") ?: "Нет выбранных элементов"

        selectedItems = arguments?.getIntegerArrayList("selectedItems")
        val buttonInt = arguments?.getInt("buttonInt")

        val collection = Collection()

        val circulant = Circulant(buttonInt!!, collection, selectedItems!!)

        var strText = circulant.getString()

        textView.text = strText
    }

}

package com.example.study_of_oriented_graph.interfaces

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.study_of_oriented_graph.R


class ResourcesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_resources, container, false)

        // Обработчик нажатия на затемняющий фон
        view.findViewById<View>(R.id.dim_background).setOnClickListener {
            // Закрыть фрагмент
            parentFragmentManager.popBackStack()
        }

        return view
    }
}

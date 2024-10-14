package com.example.study_of_oriented_graph.interfaces

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.study_of_oriented_graph.R

class GeneratOrientFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SelectedItemsAdapter
    private var selectedItems: ArrayList<Int>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_generat_orient, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Получение списка выбранных элементов
        selectedItems = arguments?.getIntegerArrayList("selectedItems")

        // Настройка RecyclerView
        recyclerView = view.findViewById(R.id.recyclerView_selected)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Инициализация адаптера с выбранными элементами
        adapter = SelectedItemsAdapter(selectedItems ?: arrayListOf())
        recyclerView.adapter = adapter

        // Дополнительная настройка интерфейса, если необходимо
    }
}

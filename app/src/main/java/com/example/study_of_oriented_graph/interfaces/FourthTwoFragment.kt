package com.example.study_of_oriented_graph.interfaces

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.study_of_oriented_graph.R
import com.example.study_of_oriented_graph.algorithms.Circulant
import com.example.study_of_oriented_graph.algorithms.Collection
import com.example.study_of_oriented_graph.databinding.FragmentFourthTwoBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FourthTwoFragment : Fragment()  {

    private var _binding: FragmentFourthTwoBinding? = null
    private val binding get() = _binding!!
    private lateinit var progressDialog: ProgressDialog
    private val collection : Collection = Collection()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFourthTwoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonInt = arguments?.getString("buttonText")?.toInt()

        // Создаем список данных для кнопок
        val buttonData = mutableListOf<Int>()
        if (buttonInt != null) {
            var count = (buttonInt.toInt()) / 2 + (buttonInt.toInt() % 2) - 1
            for (i in 1 .. count ) {
                buttonData.add(i)
            }
        } else {
            for (j in 1..14) {
                buttonData.add(j)
            }
        }

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView2)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), resources.getInteger(R.integer.count))

        val adapter = CheckBoxAdapter(buttonData)
        recyclerView.adapter = adapter

        binding.nextButton.setOnClickListener {
            val selectedItems = adapter.getCheckedItems()

            val bundle = Bundle()
            if (buttonInt != null) {
                bundle.putInt("buttonInt", buttonInt)
            }
            bundle.putIntegerArrayList("selectedItems", ArrayList(selectedItems))

            progressDialog = ProgressDialog(context)
            progressDialog.setMessage("Пожалуйста, подождите...")
            progressDialog.setCancelable(false)
            progressDialog.show()

            // Запуск навигации в отдельном потоке, чтобы не блокировать UI
            CoroutineScope(Dispatchers.IO).launch {
                //  Здесь выполняется любая дополнительная работа перед навигацией
                if (buttonInt != null) {
                    Circulant(buttonInt, collection, ArrayList(selectedItems))
                }
                bundle.putSerializable("collection_key", collection)

                withContext(Dispatchers.Main) {
                    findNavController().navigate(R.id.action_FourthTwoFragment_to_GeneratOrientFragment, bundle)
                    progressDialog.dismiss()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
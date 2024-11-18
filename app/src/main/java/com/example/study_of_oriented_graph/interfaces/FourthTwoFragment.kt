package com.example.study_of_oriented_graph.interfaces

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.SeekBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.study_of_oriented_graph.MainActivity
import com.example.study_of_oriented_graph.R
import com.example.study_of_oriented_graph.algorithms.Circulant
import com.example.study_of_oriented_graph.algorithms.Collection
import com.example.study_of_oriented_graph.databinding.FragmentFourthTwoBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FourthTwoFragment : Fragment()  {

    private var _binding: FragmentFourthTwoBinding? = null
    private val binding get() = _binding!!

    private var seekBar1Value: Int = 0
    private var seekBar2Value: Int = 0

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
        val seekBar1: SeekBar = view.findViewById(R.id.seekBar1)
        val seekBar1ValueText: TextView = view.findViewById(R.id.seekBar1Value)
        val seekBar2: SeekBar = view.findViewById(R.id.seekBar2)
        val seekBar2ValueText: TextView = view.findViewById(R.id.seekBar2Value)

        if (buttonInt != null) {
            seekBar1.max = buttonInt
            seekBar2.max = buttonInt
        }

        seekBar1.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                seekBar1Value = progress
                seekBar2.min = seekBar1Value
                seekBar1ValueText.text = "От: $seekBar1Value"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        seekBar2.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                seekBar2Value = progress
                seekBar2ValueText.text = "До: $seekBar2Value"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

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
            if (!(seekBar1Value < 3 || seekBar1Value > buttonInt!! ||
                seekBar2Value < 3 || seekBar2Value > buttonInt!! ) ) {
                val selectedItems = adapter.getCheckedItems()

                val bundle = Bundle()
                bundle.putInt("buttonInt", buttonInt)

                bundle.putInt("seekBar1", seekBar1Value)
                bundle.putInt("seekBar2", seekBar2Value)
                bundle.putIntegerArrayList("selectedItems", ArrayList(selectedItems))

                // Получить ссылку на ProgressBar
                val progressBar: ProgressBar = view.findViewById(R.id.progressBar)

                // Установить видимость ProgressBar на видимую
                progressBar.visibility = View.VISIBLE

                // Запуск навигации в отдельном потоке, чтобы не блокировать UI
                CoroutineScope(Dispatchers.IO).launch {
                    val collection = Collection()
                    Circulant(
                        buttonInt, collection, ArrayList(selectedItems), progressBar,
                        seekBar2Value
                    )

                    bundle.putSerializable("collection_key", collection)

                    withContext(Dispatchers.Main) {
                        // Скрыть ProgressBar после завершения работы
                        progressBar.visibility = View.GONE
                        findNavController().navigate(
                            R.id.action_FourthTwoFragment_to_GeneratOrientFragment,
                            bundle
                        )
                    }
                }
            }
            else {
                val rootView = (context as MainActivity).findViewById<View>(android.R.id.content)
                val snackbar = Snackbar.make(rootView, "Укажите контуры каких длинн считать",
                    Snackbar.LENGTH_INDEFINITE)
                snackbar.setAction("ОК") { snackbar.dismiss() }
                snackbar.show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
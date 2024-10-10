package com.example.study_of_oriented_graph.interfaces

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.study_of_oriented_graph.R
import com.example.study_of_oriented_graph.databinding.FragmentSeventhBinding


class SeventhFragment : Fragment() {

    private var seekBar1Value: Int = 0
    private var seekBar2Value: Int = 0

    private var _binding: FragmentSeventhBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSeventhBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val seekBar1: SeekBar = view.findViewById(R.id.seekBar1)
        val seekBar1ValueText: TextView = view.findViewById(R.id.seekBar1Value)
        val seekBar2: SeekBar = view.findViewById(R.id.seekBar2)
        val seekBar2ValueText: TextView = view.findViewById(R.id.seekBar2Value)

        // Получаем аргументы
        val vertices = arguments?.getString("vertices")?.toIntOrNull() ?: 30
        seekBar1.max = vertices
        seekBar2.max = vertices

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

        val characteristics = arguments?.getString("characteristics")
        val matrixString = arguments?.getString("matrixString")

        binding.buttonSave.setOnClickListener {
            val vertices = arguments?.getString("vertices")
            val bundle = Bundle()
            bundle.putString("matrixString", matrixString)
            bundle.putString("vertices", vertices)
            bundle.putInt("seekBar1", seekBar1Value)
            bundle.putInt("seekBar2", seekBar2Value)

            if (characteristics == "cont") {
                findNavController().navigate(R.id.action_SeventhFragment_to_СontourFragment, bundle)
            }
            else if (characteristics == "anticont") {
                findNavController().navigate(R.id.action_SeventhFragment_to_AntiContourFragment, bundle)
            }
        }
    }
}

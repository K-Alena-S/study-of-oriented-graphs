package com.example.study_of_oriented_graph.interfaces

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.study_of_oriented_graph.R


class SeventhFragment : Fragment() {

    private var seekBar1Value: Int = 0
    private var seekBar2Value: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_seventh, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val seekBar1: SeekBar = view.findViewById(R.id.seekBar1)
        val seekBar1ValueText: TextView = view.findViewById(R.id.seekBar1Value)
        val seekBar2: SeekBar = view.findViewById(R.id.seekBar2)
        val seekBar2ValueText: TextView = view.findViewById(R.id.seekBar2Value)
        val buttonSave: Button = view.findViewById(R.id.button_save)

        // Получаем аргументы
        val buttonText = arguments?.getString("buttonText")?.toIntOrNull() ?: 30
        seekBar1.max = buttonText
        seekBar2.max = buttonText

        seekBar1.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                seekBar1Value = progress
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

        buttonSave.setOnClickListener {

            // Здесь можно использовать значения seekBar1Value и seekBar2Value

            // val sharedPreferences = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
            // with(sharedPreferences.edit()) {
            //     putInt("seekBar1", seekBar1Value)
            //     putInt("seekBar2", seekBar2Value)
            //     apply()
            // }
        }
    }
}

package com.example.study_of_oriented_graph.interfaces

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.study_of_oriented_graph.R

class CheckBoxAdapter(private val buttonData: List<Int>) : RecyclerView.Adapter<CheckBoxAdapter.ViewHolder>() {

    private val checkedStates = BooleanArray(buttonData.size)

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val checkBox: CheckBox = itemView.findViewById(R.id.button_text)
        val textView: TextView = itemView.findViewById(R.id.text_view)

        fun bind(position: Int) {
            textView.text = buttonData[position].toString()
            checkBox.isChecked = checkedStates[position]

            checkBox.setOnCheckedChangeListener { _, isChecked ->
                checkedStates[position] = isChecked
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_checkbox, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = buttonData.size

    fun getCheckedItems(): List<Int> {
        return buttonData.filterIndexed { index, _ -> checkedStates[index] }
    }

}
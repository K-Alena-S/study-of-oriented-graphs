package com.example.study_of_oriented_graph.interfaces

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.study_of_oriented_graph.R

class CheckBoxAdapter(private val buttonData: List<Int>) : RecyclerView.Adapter<CheckBoxAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val checkBox: CheckBox = view.findViewById(R.id.button_text)
        val textView: TextView = view.findViewById(R.id.text_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_checkbox, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = buttonData[position].toString()
        holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
            // Здесь Вы можете обработать состояние CheckBox, если нужно
        }
    }

    override fun getItemCount() = buttonData.size
}

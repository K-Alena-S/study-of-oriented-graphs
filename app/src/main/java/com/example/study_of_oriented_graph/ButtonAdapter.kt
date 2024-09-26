package com.example.study_of_oriented_graph

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ButtonAdapter(
    private val buttons: MutableList<Int>,
    private val onButtonClick: (String) -> Unit
) : RecyclerView.Adapter<ButtonAdapter.ButtonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ButtonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_button, parent, false)
        return ButtonViewHolder(view, onButtonClick)
    }

    override fun onBindViewHolder(holder: ButtonViewHolder, position: Int) {
        holder.bind(buttons[position].toString())
    }

    override fun getItemCount() = buttons.size

    class ButtonViewHolder(
        itemView: View,
        private val onButtonClick: (String) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        private val buttonTextView: TextView = itemView.findViewById(R.id.button_text)

        init {
            buttonTextView.setOnClickListener {
                onButtonClick(buttonTextView.text.toString())
            }
        }

        fun bind(buttonText: String) {
            buttonTextView.text = buttonText
        }
    }
}


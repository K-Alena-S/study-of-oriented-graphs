package com.example.study_of_oriented_graph.interfaces

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.study_of_oriented_graph.R
import com.squareup.picasso.Picasso

class ButtonAdapter(
    private val buttons: List<ButtonData>,
    private val onButtonClick: (String) -> Unit
) : RecyclerView.Adapter<ButtonAdapter.ButtonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ButtonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_button, parent, false)
        return ButtonViewHolder(view, onButtonClick)
    }

    override fun onBindViewHolder(holder: ButtonViewHolder, position: Int) {
        holder.bind(buttons[position])
    }

    override fun getItemCount(): Int = buttons.size

    class ButtonViewHolder(
        itemView: View,
        private val onButtonClick: (String) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        private val buttonTextView: Button = itemView.findViewById(R.id.button_text)
        private val buttonImageView: ImageView = itemView.findViewById(R.id.button_image)

        init {
            buttonTextView.setOnClickListener {
                onButtonClick(buttonTextView.text.toString())
            }
        }

        fun bind(buttonData: ButtonData) {
            buttonTextView.text = buttonData.title
            Picasso.get().load(buttonData.thumbnail).into(buttonImageView) // Используем библиотеку Picasso для загрузки изображений
        }
    }
}

// Данные для кнопок
data class ButtonData(
    val title: String,
    val thumbnail: String
)



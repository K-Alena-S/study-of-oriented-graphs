package com.example.study_of_oriented_graph.interfaces

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.study_of_oriented_graph.databinding.FragmentFourthOneBinding

class FourthOneFragment : Fragment() {
    private var _binding: FragmentFourthOneBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFourthOneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Инициализация элементов интерфейса
        binding.buttonZeidelMatrix.setOnClickListener {
            // Обработка нажатия на кнопку "Матрица Зейделя"
            // Отображение полей ввода для матрицы Зейделя
        }

        binding.buttonCirculantVector.setOnClickListener {
            // Обработка нажатия на кнопку "Вектор циркулянта"
            // Отображение полей ввода для вектора циркулянта
        }

//        binding.buttonSubmit.setOnClickListener {
//            // Обработка нажатия на кнопку "Отправить"
//            // Получение введенных данных и передача их обратно в ThirdFragment
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

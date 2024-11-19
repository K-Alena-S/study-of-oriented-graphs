package com.example.study_of_oriented_graph.algorithms

import android.widget.ProgressBar

class Circulant(private val N: Int, private val col: Collection, private val dist: ArrayList<Int>,
                private var progressBar: ProgressBar, private val contourEnd: Int
) {

    private val n: Int = dist.size
    private var num: Int = 0
    private var cherche: Boolean = true
    private var str: String = ""

    init {
        val tuple = IntArray(n) { -1 }

        val str = (1 shl (n - 1))

        val pros = 1000 / str

        for (k in 0 until str) {
            if (cherche) {
                matr(tuple)
                num++
                func(tuple, n - 1)
            }
            onProgressUpdate(pros*(1+k))
        }
    }

    private fun matr(tuple: IntArray) {
        val matrix = Array(N) {
            IntArray(N) {0}
        }

        // На выбранные дистанции записываются элементы из tuple
        var count = 0
        for (k in dist) {
            matrix[0][k] = tuple[count]
            matrix[0][N-k] = -tuple[count]
            count++
        }

        // Первая строка матрицы сдвигается и записывается на остальные строки
        for (i in 1 until N) {
            for (j in 0 until N) {
                matrix[i][(j + 1) % N] = matrix[i - 1][j]
            }
        }
        val coc = ClassifCirc(matrix, col, tuple, contourEnd)

        for (i in 1 until (N/2+1))
            str += "${matrix[0][i]} "

        str += (": ${coc.getClassGraph() + 1} ${col.getList().size} :  ${coc.getContString()} \n")
    }

    fun getString(): String {
        str += "\n Классов: ${col.getList().size}"
        return str
    }

    private fun func(tuple: IntArray, count: Int) {
        tuple[count] *= -1
        if (tuple[count] == -1 && count > 0) {
            func(tuple, count - 1)
        }
    }

    fun onProgressUpdate(progress: Int) {
        progressBar.progress = progress
    }
}

package com.example.study_of_oriented_graph.algorithms

class Circulant(private val N: Int, private val col: Collection, private val dist: ArrayList<Int>) {

    private val n: Int = dist.size
    private var num: Int = 0
    private var cherche: Boolean = true
    private var str: String = ""

    init {
        val tuple = IntArray(n) { -1 }

        for (k in 0 until (1 shl (n - 1)) + 1) {
            if (cherche) {
                matr(tuple)
                num++
                func(tuple, n - 1)
            }
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
        val coc = ClassifCirc(N, matrix, col)

        for (i in matrix[0])
            str += "$i "
        str += " ^ $count"
        str += " ^ "
        str += ("${coc.getClassGraph()} ${col.getList().size} :  ${coc.getContString()} \n")
    }

    fun getString(): String {
        return str
    }

    private fun func(tuple: IntArray, count: Int) {
        tuple[count] *= -1
        if (tuple[count] == -1 && count > 0) {
            func(tuple, count - 1)
        }
    }
}

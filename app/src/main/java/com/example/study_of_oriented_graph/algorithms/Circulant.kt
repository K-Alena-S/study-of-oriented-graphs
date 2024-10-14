package com.example.study_of_oriented_graph.algorithms

class Circulant(private val N: Int, private val col: Collection) {

    private val n: Int = if (N % 2 == 0) (N - 2) / 2 else (N - 1) / 2
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
        val matrix = Array(N) { IntArray(N) }

        for (j in 1..n) {
            matrix[0][j] = tuple[j - 1]
            matrix[0][N - j] = -tuple[j - 1]
        }

        for (i in 1 until N) {
            for (j in 0 until N) {
                matrix[i][(j + 1) % N] = matrix[i - 1][j]
            }
        }

        val coc = ClassifCirc(matrix, col)
        str += ("${coc.getClassGraph()} ${col.getList().size}")
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

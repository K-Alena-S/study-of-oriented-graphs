package com.example.study_of_oriented_graph.algorithms

class ContourString(private val graphArray: Array<IntArray>, private val contourEnd: Int) {
    private var id: String = ""
    private val m: Int = 3
    private val contour: IntArray = IntArray(graphArray.size - m + 1)

    init {
        for (i in graphArray.indices) {
            val vertex = IntArray(graphArray.size)
            varRecursive(graphArray, contour, vertex, i, 0, -1)
        }

        id = contour.joinToString(" ") {
//            if (it != 0)
                it.toString()
//            else ""
        }.trim()
    }

    fun getId(): String {
        return id
    }

    fun getArray(contourBegin: Int, contourEnd: Int): IntArray {
        // Создаем новый массив нужного размера
        val size = contourEnd - contourBegin + 1
        val result = IntArray(size)

        // Копируем элементы из contour в result
        for (i in 0 until size) {
            result[i] = contour[i + contourBegin - m]
        }

        return result
    }


    private fun varRecursive(graphArray: Array<IntArray>, contour: IntArray, vertex: IntArray, a: Int, n: Int, k: Int) {
        if (a == k) {
            contour[n - m] += 1
        } else if (n < graphArray.size) {
            var updatedK = k
            if (n == 0) updatedK = a
            if (vertex[updatedK] < 2) {
                for (i in a until graphArray.size) {
                    if (graphArray[updatedK][i] == 1) {
                        var newN = n + 1
                        vertex[i] += 1
                        if (newN <= contourEnd) {
                            varRecursive(graphArray, contour, vertex, a, newN, i)
                        }
                        newN -= 1
                        vertex[i] -= 1
                    }
                }
            }
        }
    }
}

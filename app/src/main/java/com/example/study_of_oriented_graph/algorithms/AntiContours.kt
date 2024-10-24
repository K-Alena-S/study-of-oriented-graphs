package com.example.study_of_oriented_graph.algorithms

class AntiContours(private val graphArray: Array<IntArray>, private val contourEnd: Int) {
    private val m = 3 // сдвиг в массиве
    var num = 0
    var conto = 1
    private val contour: IntArray = IntArray(graphArray.size - m + 1)

    init {
        for (i in graphArray.indices) {

            if (i % 2 == 0 && graphArray.size % 2 == 1 || graphArray.size % 2 == 0) {
                val vertex = IntArray(graphArray.size)
                cont(graphArray, contour, vertex, i, 0, -1, conto)
                conto *= -1
                val vertex1 = IntArray(graphArray.size)
                cont(graphArray, contour, vertex1, i, 0, -1, conto)
                conto *= -1
            }
        }
    }

    fun getAntiCont(contourBegin: Int, contourEnd: Int): IntArray {
        val anticont = IntArray((contourEnd - contourBegin) / 2 + 1)
        for (i in anticont.indices) {
            anticont[i] = contour[2*i + contourBegin - m] / 2
        }
        return anticont
    }

    fun getAntiContAll(): IntArray {
        return contour
    }

    private fun cont(graphArray: Array<IntArray>, contour: IntArray, vertex: IntArray, a: Int, n: Int, k: Int, or: Int) {
        if (a == k) {
            if (n > 3 && or == conto) {
                num++
                contour[n - m] += 1
            }
        } else if (n < contourEnd) {
            var kVar = if (n == 0) a else k
            if (vertex[kVar] < 2) {
                for (i in a until graphArray.size) {
                    if (graphArray[kVar][i] == or && vertex[i] < 2) {
                        var nVar = n + 1
                        vertex[i] += 1
                        var orVar = or * (-1)

                        cont(graphArray, contour, vertex, a, nVar, i, orVar)
                        vertex[i] -= 1
                    }
                }
            }
        }
    }
}

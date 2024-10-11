package com.example.study_of_oriented_graph.algorithms

import org.junit.Assert
import org.junit.Test

class AntiContoursTest {

    private fun matrixToArrayInt(matrixString: String): Array<IntArray> {
        return matrixString.trim().lines().map { line ->
            line.split(", ").map { it.toInt() }.toIntArray()
        }.toTypedArray()
    }

    @Test
    fun testGetArray() {
        val graphStr = "0, -1, -1, -1, 1, 1, 1\n" +
                        "1, 0, -1, -1, -1, 1, 1\n" +
                        "1, 1, 0, -1, 1, -1, -1\n" +
                        "1, 1, 1, 0, -1, -1, -1\n" +
                        "-1, 1, -1, 1, 0, -1, 1\n" +
                        "-1, -1, 1, 1, 1, 0, -1\n" +
                        "-1, -1, 1, 1, -1, 1, 0"
        val graphArray = matrixToArrayInt(graphStr)
        val contourEnd = 6
        val contourString = AntiContours(graphArray, contourEnd)

        // Проверка получения массива
        val contourBegin = 4
        val expectedArray = intArrayOf(3, 1)
        Assert.assertArrayEquals(expectedArray, contourString.getAntiCont(contourBegin, contourEnd))
    }
}
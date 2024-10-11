package com.example.study_of_oriented_graph.algorithms

import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Test

class ContourStringTest {

    private fun matrixToArrayInt(matrixString: String): Array<IntArray> {
        return matrixString.trim().lines().map { line ->
            line.split(", ").map { it.toInt() }.toIntArray()
        }.toTypedArray()
    }

    @Test
    fun testGetId() {
        val graphStr = "0, -1, -1, 1, 1\n" +
                        "1, 0, -1, -1, 1\n" +
                        "1, 1, 0, -1, -1\n" +
                        "-1, 1, 1, 0, -1\n" +
                        "-1, -1, 1, 1, 0"
        val graphArray = matrixToArrayInt(graphStr)
        val contourEnd = 5
        val contourString = ContourString(graphArray, contourEnd)

        // Проверка получения ID
        val expectedId = "5 5 2"
        assertEquals(expectedId, contourString.getId())
    }

    @Test
    fun testGetArray() {
        val graphStr = "0, -1, -1, 1, 1\n" +
                        "1, 0, -1, -1, 1\n" +
                        "1, 1, 0, -1, -1\n" +
                        "-1, 1, 1, 0, -1\n" +
                        "-1, -1, 1, 1, 0"
        val graphArray = matrixToArrayInt(graphStr)
        val contourEnd = 5
        val contourString = ContourString(graphArray, contourEnd)

        // Проверка получения массива
        val contourBegin = 3
        val expectedArray = intArrayOf(5, 5, 2)
        assertArrayEquals(expectedArray, contourString.getArray(contourBegin, contourEnd))
    }

}

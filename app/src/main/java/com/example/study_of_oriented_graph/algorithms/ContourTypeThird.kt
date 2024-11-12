package com.example.study_of_oriented_graph.algorithms

class ContourTypeThird (private val col: Collection, private val N: Int) {

    // Создаем список массивов
    val components = mutableListOf<IntArray>() // 0 1 2 / 0 1 3 / 0 1 4 ...
    val typesFrag = mutableListOf<IntArray>()

    init {
        createComponents(N)

        for (matrix in col.getList()) {
            countTypes(N, copyMatrix(matrix))
        }

    }

    fun getTypesFrag (k: Int): IntArray{
        return typesFrag[k]
    }

    fun createComponents(N: Int) {

        for (k in 0 until N) {
            for (a in k+1 until N) {
                for (s in a+1 until N) {
                    val array = IntArray(3) { 0 }
                    array[0] = k
                    array[1] = a
                    array[2] = s
                    components.add(array)
                }
            }
        }
    }

    fun copyMatrix(original: Array<IntArray>): Array<IntArray> {
        return Array(original.size) { i ->
            original[i].copyOf()
        }
    }

    fun countTypes(N: Int, matrix: Array<IntArray>) {
        val array = IntArray(7) { 0 }

        for (comp in components) {

            var count = 0

            if (matrix[comp[0]][comp[1]] != 0) count++
            if (matrix[comp[1]][comp[2]] != 0) count++
            if (matrix[comp[2]][comp[0]] != 0) count++

            when (count) {
                0 -> array[0]++
                1 -> array[1]++
                2 -> {
                    var point = -1
                    if (matrix[comp[0]][comp[1]] == matrix[comp[2]][comp[1]] ) point = 1
                    else if (matrix[comp[0]][comp[2]] == matrix[comp[1]][comp[2]] ) point = 2
                    else if (matrix[comp[1]][comp[0]] == matrix[comp[2]][comp[0]] ) point = 0

                    if (point == -1)  array[4]++
                    else if (matrix[comp[(point+1)%3]][comp[point]] == 1) array[2]++
                    else array[3]++

                }
                3 -> {
                    if (matrix[comp[0]][comp[1]] == matrix[comp[1]][comp[2]]
                        && matrix[comp[2]][comp[0]] == matrix[comp[0]][comp[1]] ) {
                        array[5]++
                    }
                    else array[6]++
                }
            }

        }
        typesFrag.add(array.copyOf())
    }
}
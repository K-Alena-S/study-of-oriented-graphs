package com.example.study_of_oriented_graph.algorithms

import java.util.Arrays

class ClassifCirc(graph: Array<IntArray>, col: Collection, tuple: IntArray, contourEnd: Int) {

    private var classgraph: Int = 0
    private var resId: String = ""

    init {
        val a = graph.map { it.clone() }.toTypedArray()
        val cs = ContourString(a, contourEnd)
        resId = cs.getId()

        var cherche = true

        var n = 0;
        for (i in col.getStrList()) {
            if (resId.equals(i, ignoreCase = true)) {
                classgraph = n;
                col.setVector(n, Arrays.toString(tuple))
                cherche = false
                break
            }
            n++
        }

        if (cherche) {
            classgraph = col.getStrList().size
            col.setList(graph)
            col.setStrList(resId)
            col.setVector(n, Arrays.toString(tuple))
        }
    }

    fun getClassGraph(): Int {
        return classgraph
    }

    fun getContString(): String {
        return resId
    }
}

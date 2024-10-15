package com.example.study_of_oriented_graph.algorithms

class ClassifCirc(N: Int, graph: Array<IntArray>, col: Collection) {

    private var classgraph: Int = 0
    private var resId: String = ""

    init {
        val a = graph.map { it.clone() }.toTypedArray()
        val cs = ContourString(a, N)
        resId = cs.getId()

        var cherche = true

        var n = 0;
        for (i in col.getStrList()) {
            if (resId.equals(i, ignoreCase = true)) {
                classgraph = n;
                cherche = false
                break
            }
            n++
        }

        if (cherche) {
            classgraph = col.getStrList().size
            col.setList(graph)
            col.setStrList(resId)
        }
    }

    fun getClassGraph(): Int {
        return classgraph
    }

    fun getContString(): String {
        return resId
    }
}

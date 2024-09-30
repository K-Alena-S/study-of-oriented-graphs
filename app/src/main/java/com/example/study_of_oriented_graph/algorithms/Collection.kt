package com.example.study_of_oriented_graph.algorithms

class Collection {
    private val graphList: MutableList<Array<IntArray>> = mutableListOf()
    private val strList: MutableList<String> = mutableListOf()

    fun setStrList(str: String) {
        strList.add(str)
    }

    fun getStrList(): List<String> {
        return strList
    }

    fun setList(graph: Array<IntArray>) {
        graphList.add(graph)
    }

    fun getList(): List<Array<IntArray>> {
        return graphList
    }
}

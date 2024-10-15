package com.example.study_of_oriented_graph.algorithms

interface CollectionInterface {
    fun setStrList(str: String)
    fun getStrList(): List<String>
    fun setList(graph: Array<IntArray>)
    fun getList(): List<Array<IntArray>>
}

class Collection : CollectionInterface {
    private val graphList: MutableList<Array<IntArray>> = mutableListOf()
    private val strList: MutableList<String> = mutableListOf()

    override fun setStrList(str: String) {
        strList.add(str)
    }

    override fun getStrList(): List<String> {
        return strList
    }

    override fun setList(graph: Array<IntArray>) {
        graphList.add(graph)
    }

    override fun getList(): List<Array<IntArray>> {
        return graphList
    }
}

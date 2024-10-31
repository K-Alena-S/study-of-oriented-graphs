package com.example.study_of_oriented_graph.algorithms

interface CollectionInterface {
    fun setStrList(str: String)
    fun getStrList(): List<String>
    fun setList(graph: Array<IntArray>)
    fun getList(): List<Array<IntArray>>
    fun setVector(k: Int, vector: String)
    fun getVector(k: Int): List<String>
}

class Collection : CollectionInterface {
    private val graphList: MutableList<Array<IntArray>> = mutableListOf()
    private val strList: MutableList<String> = mutableListOf()
    private val vectorListList: MutableList<MutableList<String>> = mutableListOf()

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

    override fun setVector(k: Int, vector: String) {
        while (vectorListList.size <= k) {
            vectorListList.add(mutableListOf())
        }
        vectorListList[k].add(vector)
    }

    override fun getVector(k: Int): List<String> {
        return vectorListList.getOrElse(k) { emptyList() }
    }
}

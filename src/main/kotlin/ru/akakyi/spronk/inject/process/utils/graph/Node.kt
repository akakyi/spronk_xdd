package ru.akakyi.spronk.inject.process.utils.graph

data class Node<Type : Any>(
    val value: Type,
    val parent: Node<Type>?,
    val childs: MutableList<Node<Type>>,
    val level: Int
)

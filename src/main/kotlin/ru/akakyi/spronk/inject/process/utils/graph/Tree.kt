package ru.akakyi.spronk.inject.process.utils.graph

//TODO Защита от циклов! Пока просто номинальная, один Node может ссылаться на другой Node,
//TODO при этом value в них будут одинаковы, но по факту это разные ноды дерева, так что по факту не цикл)))0)
//TODO но работать будет крыво
class Tree<Type : Any>(
    root: Type
) : Iterable<Type> {

    private val rootNode = Node(
        value = root,
        parent = null,
        childs = mutableListOf(),
        level = 0
    )

    fun addChild(parent: Type) {

    }

    override fun iterator() = TreeIterator(this)

    class TreeIterator<Type : Any>(
        private val tree: Tree<Type>
    ) : Iterator<Type> {



        override fun hasNext(): Boolean {
            
        }

        override fun next(): Type {
            TODO("Not yet implemented")
        }

    }

}

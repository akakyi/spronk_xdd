package ru.akakyi.spronk.inject.process.collect

import ru.akakyi.spronk.inject.process.BeanDescription

class PackageBeanDescriptionCollector(
    val basePackage: String
) : BeanDescriptionCollector {

    override fun getDescriptions(): Set<BeanDescription> {
        return setOf()
    }

}

package ru.akakyi.spronk.inject.process.collect

import ru.akakyi.spronk.inject.process.BeanDescription

interface BeanDescriptionCollector {

    fun getDescriptions(): Set<BeanDescription>

}

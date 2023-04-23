package ru.akakyi.spronk.inject.process.collect

import ru.akakyi.spronk.inject.process.dto.BeanDescription

interface BeanDescriptionCollector {

    fun getDescriptions(): Set<BeanDescription>

}

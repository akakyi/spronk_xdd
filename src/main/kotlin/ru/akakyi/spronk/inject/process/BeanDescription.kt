package ru.akakyi.spronk.inject.process

data class BeanDescription(
    val name: String,
    val dependenciesNames: Set<String>
)

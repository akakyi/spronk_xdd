package ru.akakyi.spronk.inject.process

data class BeanDescription(
    val classFullName: String,
    val name: String,
    val dependenciesNames: List<String>
)

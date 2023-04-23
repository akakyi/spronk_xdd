package ru.akakyi.spronk.inject.process.dto

data class BeanDescription(
    val classFullName: String,
    val name: String,
    val dependenciesNames: List<DependencyDescription>
)

package ru.akakyi.spronk.inject.process.utils

fun List<*>.hasNull() =
    this.indexOf(null) >= 0

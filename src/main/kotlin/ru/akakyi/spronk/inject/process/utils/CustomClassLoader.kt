package ru.akakyi.spronk.inject.process.utils

import kotlin.reflect.KClass

interface CustomClassLoader {

    fun execute(): Set<KClass<*>>

}

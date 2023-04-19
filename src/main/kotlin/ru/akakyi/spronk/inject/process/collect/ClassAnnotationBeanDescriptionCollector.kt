package ru.akakyi.spronk.inject.process.collect

import ru.akakyi.spronk.inject.process.BeanDescription
import ru.akakyi.spronk.inject.process.utils.CustomClassLoader
import kotlin.reflect.KClass

abstract class ClassAnnotationBeanDescriptionCollector(
    private val packageClassLoaderUseCase: CustomClassLoader
) : BeanDescriptionCollector {

    override fun getDescriptions(): Set<BeanDescription> {
        val classes = packageClassLoaderUseCase.execute()
        classes.forEach(::println)
        return setOf()
    }

    protected abstract val targetBeanClassAnnotations: Set<KClass<out Annotation>>

}

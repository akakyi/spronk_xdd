package ru.akakyi.spronk.inject.process.collect

import ru.akakyi.spronk.inject.annotations.PutIn
import ru.akakyi.spronk.inject.process.BeanDescription
import ru.akakyi.spronk.inject.process.utils.CustomClassLoader
import kotlin.reflect.KClass

abstract class ClassAnnotationBeanDescriptionCollector(
    private val packageClassLoaderUseCase: CustomClassLoader
) : BeanDescriptionCollector {

    override fun getDescriptions(): Set<BeanDescription> {
        val classes = packageClassLoaderUseCase.execute()

        return classes.filter { klass ->
            klass.annotations.any { annotation ->
                targetBeanClassAnnotations.any { it.isInstance(annotation) }
            }
        }.mapNotNull { klass ->
            klass.simpleName?.let {
                it to klass.constructors.first()
            }
        }.map { nameWithConstr ->
            val argsNames = nameWithConstr.second.parameters
                .map { param ->
                    param.annotations.first { it == PutIn::class } as PutIn
                }.map {
                    it.name
                }
            BeanDescription(
                classFullName = ,
                name = nameWithConstr.first,
                dependenciesNames = argsNames
            )
        }.toSet()
    }

    protected abstract val targetBeanClassAnnotations: Set<KClass<out Annotation>>

}

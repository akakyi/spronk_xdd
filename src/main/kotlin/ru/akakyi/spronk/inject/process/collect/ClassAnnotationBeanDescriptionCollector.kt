package ru.akakyi.spronk.inject.process.collect

import ru.akakyi.spronk.inject.annotations.Component
import ru.akakyi.spronk.inject.annotations.PutIn
import ru.akakyi.spronk.inject.process.dto.BeanDescription
import ru.akakyi.spronk.inject.process.dto.DependencyDescription
import ru.akakyi.spronk.inject.process.utils.CustomClassLoader
import kotlin.reflect.KFunction

class ClassAnnotationBeanDescriptionCollector(
    private val packageClassLoaderUseCase: CustomClassLoader
) : BeanDescriptionCollector {

    companion object {

        val COMPONENT_ANNOTATION_CLASS = Component::class

        val INJECT_ANNOTATION_CLASS = PutIn::class

    }

    override fun getDescriptions(): Set<BeanDescription> {
        val classes = packageClassLoaderUseCase.execute()

        return classes.mapNotNull { klass ->
            val annotation = klass.annotations.firstOrNull { annotation ->
                COMPONENT_ANNOTATION_CLASS.isInstance(annotation)
            }?.let { COMPONENT_ANNOTATION_CLASS.javaObjectType.cast(it) }
            annotation?.let { klass to annotation }
        }.mapNotNull { klassAndAnnotation ->
            klassAndAnnotation.first.qualifiedName?.let {
                PreDescription(
                    className = it,
                    beanName = klassAndAnnotation.second.name,
                    //TODO как вытащить праймари конструктор?
                    constructor = klassAndAnnotation.first.constructors.first()
                )
            }
        }.map { nameWithConstr ->
            val argsNames = nameWithConstr.constructor.parameters
                .map { param ->
                    val annotation = param.annotations.firstOrNull {
                        INJECT_ANNOTATION_CLASS.isInstance(it)
                    } ?: throw RuntimeException(
                        "One of parameter ${nameWithConstr.className} has no $INJECT_ANNOTATION_CLASS"
                    )
                    annotation to param.index
                }.map {
                    val annotation = INJECT_ANNOTATION_CLASS.javaObjectType.cast(it.first)
                    annotation.name to it.second
                }
            BeanDescription(
                classFullName = nameWithConstr.className,
                name = nameWithConstr.beanName,
                dependenciesNames = argsNames.map {
                    DependencyDescription(
                        beanName = it.first,
                        argIndex = it.second
                    )
                }
            )
        }.toSet()
    }

    private data class PreDescription(
        val className: String,
        val beanName: String,
        val constructor: KFunction<*>
    )

}

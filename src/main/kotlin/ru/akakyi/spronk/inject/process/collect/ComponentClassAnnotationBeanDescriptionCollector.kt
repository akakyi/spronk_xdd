package ru.akakyi.spronk.inject.process.collect

import ru.akakyi.spronk.inject.annotations.Component
import ru.akakyi.spronk.inject.process.utils.CustomClassLoader

class ComponentClassAnnotationBeanDescriptionCollector(
    packageClassLoaderUseCase: CustomClassLoader
) : ClassAnnotationBeanDescriptionCollector(packageClassLoaderUseCase) {

    override val targetBeanClassAnnotations = setOf(Component::class)

}

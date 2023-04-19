import ru.akakyi.spronk.inject.process.collect.ComponentClassAnnotationBeanDescriptionCollector
import ru.akakyi.spronk.inject.process.utils.PackageClassLoaderUseCase

fun main(args: Array<String>) {
    val loader = PackageClassLoaderUseCase("ru.akakyi.test")
    val collector = ComponentClassAnnotationBeanDescriptionCollector(loader)
    collector.getDescriptions()
}
import ru.akakyi.spronk.inject.process.collect.ClassAnnotationBeanDescriptionCollector
import ru.akakyi.spronk.inject.process.utils.PackageClassLoaderUseCase

fun main(args: Array<String>) {
    val loader = PackageClassLoaderUseCase("ru.akakyi.test")
    val collector = ClassAnnotationBeanDescriptionCollector(loader)
    collector.getDescriptions().forEach(::println)
}
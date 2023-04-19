package ru.akakyi.spronk.inject.process.utils

import java.io.File
import java.lang.RuntimeException
import kotlin.reflect.KClass

class PackageClassLoaderUseCase(
    private val basePackage: String
) : CustomClassLoader {

    //TODO не умеет в 2 и более класса в файле и в принципе страшно и криво
    override fun execute(): Set<KClass<*>> {
        //Вынес из конструктора дабы связность понизить. Хз, буду ли я дальше юзать именно класслодер
        val classLoader = ClassLoader.getSystemClassLoader()
        val directory = getPackageAbsoluteDirectory(
            pack = basePackage,
            classLoader = classLoader
        )
        return File(directory).walk()
            .filterNot { it.isDirectory }
            .map {
                val result = it.path
                    .replace("/", ".")
                    .substringAfterLast("$basePackage.")
                    .replace(".class", "")
                "$basePackage.$result"
            }.map {
                classLoader.loadClass(it).kotlin
            }.toSet()
    }

    private fun getPackageAbsoluteDirectory(pack: String, classLoader: ClassLoader) =
        classLoader.getResource("")?.let {
            it.path + pack.replace(".", "/")
        } ?: throw RuntimeException("Resource not found?")

}

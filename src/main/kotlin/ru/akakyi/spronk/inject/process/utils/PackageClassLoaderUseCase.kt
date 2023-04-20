package ru.akakyi.spronk.inject.process.utils

import java.io.File
import java.lang.RuntimeException
import kotlin.reflect.KClass

class PackageClassLoaderUseCase(
    private val basePackage: String,
    private val classLoader: ClassLoader = ClassLoader.getSystemClassLoader()
) : CustomClassLoader {

    //TODO не умеет в 2 и более класса в файле и в принципе страшно и криво
    override fun execute(): Set<KClass<*>> {
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

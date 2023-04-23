package ru.akakyi.test

import ru.akakyi.spronk.inject.annotations.Component
import ru.akakyi.spronk.inject.annotations.PutIn

@Component(name = "CompBLeft")
class CompBLeft(
    @PutIn(name = "CompCLeft") private val compCLeft: CompCLeft
)

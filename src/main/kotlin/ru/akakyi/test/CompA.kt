package ru.akakyi.test

import ru.akakyi.spronk.inject.annotations.Component
import ru.akakyi.spronk.inject.annotations.PutIn

@Component(name = "CompA")
class CompA(
    @PutIn(name = "CompBLeft") compBLeft: CompBLeft,
    @PutIn(name = "CompB") compB: CompB
)

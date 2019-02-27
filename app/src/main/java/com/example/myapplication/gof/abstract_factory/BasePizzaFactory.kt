package com.example.myapplication.gof.abstract_factory

import com.example.myapplication.gof.IPizza

abstract class BasePizzaFactory {
    abstract fun createPizza(type: String): IPizza
}
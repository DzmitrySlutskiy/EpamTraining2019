package com.example.myapplication.gof.abstract_factory

import com.example.myapplication.gof.CheesePizza
import com.example.myapplication.gof.PepperoniPizza
import com.example.myapplication.gof.builder.Pizza


class PizzaFactory : BasePizzaFactory() {
    override fun createPizza(type: String): Pizza {
        val pizza: Pizza

        when (type.toLowerCase()) {
            "cheese" -> pizza = CheesePizza()
            "pepperoni" -> pizza = PepperoniPizza()
            else -> throw IllegalArgumentException("No such pizza.")
        }

        return pizza
    }
}
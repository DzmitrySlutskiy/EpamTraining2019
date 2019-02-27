package com.example.myapplication.gof.abstract_factory

import com.example.myapplication.gof.IPizza
import com.example.myapplication.gof.builder.Pizza

class PizzaFactoryStub : BasePizzaFactory() {
    override fun createPizza(type: String): IPizza {
        return Pizza.Builder().setId(0).setTitle("dg").setIngridients("i").build()
    }
}
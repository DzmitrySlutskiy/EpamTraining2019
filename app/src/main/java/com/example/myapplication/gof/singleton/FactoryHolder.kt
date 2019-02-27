package com.example.myapplication.gof.singleton

import com.example.myapplication.gof.abstract_factory.BasePizzaFactory
import com.example.myapplication.gof.abstract_factory.PizzaFactory

class FactoryHolder {
    private var productionHouse: BasePizzaFactory? = null

    fun getInstance(): BasePizzaFactory {

        if (productionHouse == null) {
            synchronized(BasePizzaFactory::class.java) {
                if (productionHouse == null) {
                    productionHouse = PizzaFactory()
//                    productionHouse = PizzaFactory()
                }
            }
        }

        return productionHouse!!
    }
}


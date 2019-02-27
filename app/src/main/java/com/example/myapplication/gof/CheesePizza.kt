package com.example.myapplication.gof

import com.example.myapplication.gof.builder.Pizza


class CheesePizza : Pizza() {
    override fun getTitle() = "CheesePizza"
}
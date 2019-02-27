package com.example.myapplication.gof.adapter

import com.example.myapplication.gof.IPizza


interface OnItemClickListener {
    fun onItemClicked(pPizza: IPizza)
}
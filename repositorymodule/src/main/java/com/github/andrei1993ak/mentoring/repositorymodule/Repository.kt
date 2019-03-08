package com.github.andrei1993ak.mentoring.repositorymodule

import com.github.andrei1993ak.mentoring.othernetworkmodule.Api

class Repository {
    fun getUser(): String {
        return Api().getCall()
    }
}
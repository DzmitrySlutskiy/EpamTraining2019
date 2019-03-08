package com.github.andrei1993ak.mentoring.awesomemodule

import com.github.andrei1993ak.mentoring.networkmodule.OldApi

class OldRepository {
    fun getUser() = OldApi().getCall()
}
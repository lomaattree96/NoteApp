package com.example.tasks


//INTEGRATION UI TEST

import android.app.Activity
import android.app.Application
import android.content.Context

import androidx.test.runner.AndroidJUnitRunner
import dagger.hilt.android.testing.HiltTestApplication

//not using note application as root app instead custom one junit is used so test runner is made so define our actual application class


        //we need to register it in build gradle app also

//add test instrumentation runner in gradle


class HiltTestRunner : AndroidJUnitRunner() {

    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {
        return super.newApplication(cl, HiltTestApplication::class.java.name, context)
    }
}
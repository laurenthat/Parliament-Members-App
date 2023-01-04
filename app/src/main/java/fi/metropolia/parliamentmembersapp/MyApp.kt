package fi.metropolia.parliamentmembersapp

import android.app.Application
import android.content.Context
import android.util.Log

//Date: 10.10.2022
//Laurentiu Sebastian Hategan
//Student id: 2112621
//Class gives the application context and the state of the application

class MyApp: Application() {
    companion object {
        lateinit var appContext: Context
    }
    override fun onCreate() {
        super.onCreate()
        Log.d("QQQ", "MyApp onCreate()")
        appContext = applicationContext
    }
}

package uz.gita.top_best_book_finish.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext
import uz.gita.top_best_book_finish.data.source.local.SharedPref

@HiltAndroidApp
class App :Application(){
    override fun onCreate() {
        super.onCreate()
        SharedPref.init(this)
    }

}
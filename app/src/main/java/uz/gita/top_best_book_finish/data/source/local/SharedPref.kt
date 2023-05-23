package uz.gita.top_best_book_finish.data.source.local

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPref {


    companion object {
        private const val SHARED_PREF = "shared_pref"
        private const val BOOK_NAME = "book_name"
        private const val SAVED_PAGE = "saved_page"
        private const val TOTAL_PAGE = "total_page"
        private const val PERCENTAGE = "percentage"

        private lateinit var pref: SharedPreferences
        private lateinit var editor: SharedPreferences.Editor

        fun init(context: Context) {
            pref = context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)
            editor = pref.edit()
        }
    }


    var bookName: String
        set(value) = editor.putString(BOOK_NAME, value).apply()
        get() = pref.getString(BOOK_NAME, "").toString()

    var savedPage: Int
        set(value) = editor.putInt(SAVED_PAGE, value).apply()
        get() = pref.getInt(SAVED_PAGE, 0)

    var totalPage: Int
        set(value) = editor.putInt(TOTAL_PAGE, value).apply()
        get() = pref.getInt(TOTAL_PAGE, 0)

    var percentage: Int
        set(value) = editor.putInt(PERCENTAGE, value).apply()
        get() = pref.getInt(PERCENTAGE, 0)



}
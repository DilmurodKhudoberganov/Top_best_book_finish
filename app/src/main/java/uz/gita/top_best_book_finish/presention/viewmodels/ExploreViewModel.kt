package uz.gita.top_best_book_finish.presention.viewmodels

import androidx.lifecycle.LiveData
import uz.gita.top_best_book_finish.data.common.AllBooksData
import uz.gita.top_best_book_finish.data.common.CategoryData

interface ExploreViewModel {

    val booksData: LiveData<List<AllBooksData>>
    val categoriesData: LiveData<List<CategoryData>>
    val errorData: LiveData<String>
}
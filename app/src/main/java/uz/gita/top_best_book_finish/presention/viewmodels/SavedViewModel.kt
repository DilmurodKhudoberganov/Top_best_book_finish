package uz.gita.top_best_book_finish.presention.viewmodels

import androidx.lifecycle.LiveData
import uz.gita.top_best_book_finish.data.common.BookData

interface SavedViewModel {
    val booksData: LiveData<List<BookData>>
    val errorData: LiveData<String>
}
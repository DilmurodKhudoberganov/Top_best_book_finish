package uz.gita.top_best_book_finish.domain.repository

import android.content.Context
import kotlinx.coroutines.flow.Flow
import uz.gita.top_best_book_finish.data.common.BookData
import uz.gita.top_best_book_finish.data.common.CategoryData

interface AppRepository {


    fun getAllBooks(): Flow<Result<List<BookData>>>

    fun downloadBookByUrl(context: Context, book: BookData): Flow<Result<BookData>>

    fun getCategories(): Flow<Result<List<CategoryData>>>

    fun getRecommendedBooks(): Flow<Result<List<BookData>>>

    fun getSavedBooks(context: Context): Flow<Result<List<BookData>>>
}
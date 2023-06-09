package uz.gita.bookappwithfirebase.presentation.viewmodels.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.top_best_book_finish.data.common.BookData
import uz.gita.top_best_book_finish.domain.repository.impl.AppRepositoryImpl
import uz.gita.top_best_book_finish.presention.viewmodels.HomeViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModelImpl @Inject constructor(
    private val repository: AppRepositoryImpl
) : HomeViewModel, ViewModel() {

    override val booksData = MutableLiveData<List<BookData>>()
    override val errorData = MutableLiveData<String>()

    init {
        getAllData()
    }

    private fun getAllData() {
        repository.getRecommendedBooks()
            .onEach { bookData ->
                bookData.onSuccess { booksData.value = it }
                bookData.onFailure { errorData.value = it.message }
            }.launchIn(viewModelScope)
    }
}
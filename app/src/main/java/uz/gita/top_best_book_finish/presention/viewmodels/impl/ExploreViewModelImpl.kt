package uz.gita.bookappwithfirebase.presentation.viewmodels.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.top_best_book_finish.data.common.AllBooksData
import uz.gita.top_best_book_finish.data.common.CategoryData
import uz.gita.top_best_book_finish.domain.repository.impl.AppRepositoryImpl
import uz.gita.top_best_book_finish.presention.viewmodels.ExploreViewModel
import javax.inject.Inject

@HiltViewModel
class ExploreViewModelImpl @Inject constructor(
    private val repository: AppRepositoryImpl
) : ExploreViewModel, ViewModel() {

    override val booksData = MutableLiveData<List<AllBooksData>>()
    override val categoriesData = MutableLiveData<List<CategoryData>>()
    override val errorData = MutableLiveData<String>()

    init {
        getAllData()
    }

    fun getBooksByCategory(categoryNameList: List<String>) {
        val list = ArrayList<AllBooksData>()
        for (index in categoryNameList.indices) {
            repository.getBooksByCategory(categoryNameList[index])
                .onEach { result ->
                    result.onSuccess {
                        list.add(AllBooksData(categoryNameList[index], it))
                        booksData.value = list
                    }
                    result.onFailure { }
                }.launchIn(viewModelScope)
        }
    }

    private fun getAllData() {
        repository.getCategories()
            .onEach { categoryList ->
                categoryList.onSuccess { categoriesData.value = it }
                categoryList.onFailure { errorData.value = it.message }
            }.launchIn(viewModelScope)
    }
}
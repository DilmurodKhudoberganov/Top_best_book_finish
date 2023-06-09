package uz.gita.top_best_book_finish.data.common

import java.io.Serializable

data class BookData(
    val author: String = "",
    val name: String = "",
    val bookCoverUrl: String = "",
    val page: String = "",
    val genre: String = "",
    val year: String = "",
    val bookUrl: String = ""
) : Serializable {
}
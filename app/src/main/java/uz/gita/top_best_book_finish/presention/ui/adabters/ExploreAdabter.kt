package uz.gita.top_best_book_finish.presention.ui.adabters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import uz.gita.top_best_book_finish.data.common.AllBooksData
import uz.gita.top_best_book_finish.data.common.BookData
import uz.gita.top_best_book_finish.databinding.VerticalItemBinding
import javax.inject.Inject

class ExploreAdabter @Inject constructor() : Adapter<ExploreAdabter.ItemHolder>() {
    @Inject
    lateinit var innerAdapter: HorizontalExploreAdabter

    private var list: List<AllBooksData> = ArrayList()

    fun setData(l: List<AllBooksData>) {
        list = l
        notifyDataSetChanged()
    }

    private var clickListener: ((BookData) -> Unit)? = null

    fun setClickListener(l: (BookData) -> Unit) {
        clickListener = l
    }

    inner class ItemHolder(private val binding: VerticalItemBinding) :
        ViewHolder(binding.root) {

        fun bind() {
            list[adapterPosition].apply {
                innerAdapter.setData(this.books)
                binding.horizontalRv.adapter = innerAdapter
                binding.horizontalRv.layoutManager =
                    LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)

                binding.category.text = this.categoryName

                innerAdapter.setClickListener {
                    clickListener?.invoke(it)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            VerticalItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind()
    }
}
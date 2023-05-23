package uz.gita.top_best_book_finish.presention.ui.adabters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.gita.top_best_book_finish.R
import uz.gita.top_best_book_finish.data.common.BookData
import uz.gita.top_best_book_finish.databinding.ItemRecommendBookBinding
import javax.inject.Inject

class HomeAdabter  @Inject constructor(): RecyclerView.Adapter<HomeAdabter.ItemHolder>() {

    private var list: List<BookData> = ArrayList()

    fun setData(l: List<BookData>) {
        list = l
        notifyItemRangeChanged(0, list.size)
    }

    private var clickListener: ((BookData) -> Unit)? = null

    fun setClickListener(l: (BookData) -> Unit) {
        clickListener = l
    }

    inner class ItemHolder(private val binding: ItemRecommendBookBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                clickListener?.invoke(list[adapterPosition])
            }
        }

        fun bind() {
            binding.apply {
                txtTitle.text = list[adapterPosition].name
                txtAuthor.text = list[adapterPosition].author

                val imgUrl = list[adapterPosition].bookCoverUrl

                if (imgUrl == "") {
                    imgIcon.setImageResource(R.drawable.icon_book)
                } else {
                    Glide.with(binding.root.context).load(list[adapterPosition].bookCoverUrl)
                        .into(imgIcon)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            ItemRecommendBookBinding.inflate(
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
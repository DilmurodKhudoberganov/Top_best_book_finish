package uz.gita.top_best_book_finish.presention.ui.adabters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import uz.gita.top_best_book_finish.R
import uz.gita.top_best_book_finish.data.common.BookData
import uz.gita.top_best_book_finish.databinding.ItemBookBinding
import javax.inject.Inject

class HorizontalExploreAdabter @Inject constructor():RecyclerView.Adapter<HorizontalExploreAdabter.ItemHolder>() {

    private var list: List<BookData> = ArrayList()

    fun setData(l: List<BookData>) {
        list = l
        notifyDataSetChanged()
    }

    private var clickListener: ((BookData) -> Unit)? = null

    fun setClickListener(l: (BookData) -> Unit) {
        clickListener = l
    }

    inner class ItemHolder(private val binding: ItemBookBinding) : ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                clickListener?.invoke(list[adapterPosition])
            }
        }

        fun bind() {
            binding.apply {
                txtTitle.text = list[adapterPosition].name

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
            ItemBookBinding.inflate(
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
package uz.gita.top_best_book_finish.presention.ui.screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.bookappwithfirebase.presentation.ui.screens.SavedBooksScreenDirections
import uz.gita.bookappwithfirebase.presentation.viewmodels.impl.SavedViewModelImpl
import uz.gita.bookappwithfirebase.utils.logd
import uz.gita.bookappwithfirebase.utils.toasT
import uz.gita.top_best_book_finish.R
import uz.gita.top_best_book_finish.databinding.ScreenSavedBinding
import uz.gita.top_best_book_finish.presention.ui.adabters.SavedAdapter
import java.io.File
import javax.inject.Inject

@AndroidEntryPoint
class SavedBooksScreen : Fragment(R.layout.screen_saved) {

    private val binding by viewBinding(ScreenSavedBinding::bind)
    private val viewModel by viewModels<SavedViewModelImpl>()

    @Inject
    lateinit var adapter: SavedAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAllData(requireContext())

        adapter.setClickListener {
            //SavedBooksScreenDirections
            findNavController().navigate(
                SavedBooksScreenDirections.actionSavedBooksScreenToBookReadScreen(
                    it.name, 0, it.page.toInt()
                )
            )
        }

        adapter.setDeleteClickListener {
            val file = File(requireContext().filesDir, it.name)
            val deleted = if (file.exists()) file.delete() else false

            if (deleted) {
                toasT("Book deleted")
            } else logd("File not found")
            adapter.notifyDataSetChanged()
        }

        binding.apply {
            recycler.layoutManager = GridLayoutManager(requireContext(), 2)
            recycler.adapter = adapter
        }

        viewModel.booksData.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                binding.apply {
                    imgNoBooks.visibility = View.VISIBLE
                    txtNoBookTitle.visibility = View.VISIBLE
                }
            } else {
                binding.apply {
                    imgNoBooks.visibility = View.GONE
                    txtNoBookTitle.visibility = View.GONE
                }
            }
            adapter.setData(it)
        }

        viewModel.errorData.observe(viewLifecycleOwner) {
            logd("SavedScreen error = $it")
        }
    }
}
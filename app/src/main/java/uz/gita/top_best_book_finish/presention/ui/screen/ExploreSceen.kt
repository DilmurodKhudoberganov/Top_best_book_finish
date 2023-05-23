package uz.gita.top_best_book_finish.presention.ui.screen

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.top_best_book_finish.R
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.bookappwithfirebase.presentation.ui.screens.ExploreScreenDirections
import uz.gita.bookappwithfirebase.presentation.viewmodels.impl.ExploreViewModelImpl
import uz.gita.top_best_book_finish.databinding.ScreenExploreBinding
import uz.gita.top_best_book_finish.presention.ui.adabters.ExploreAdabter
import uz.gita.bookappwithfirebase.utils.Constants
import javax.inject.Inject

@AndroidEntryPoint
class ExploreSceen : Fragment(R.layout.screen_explore) {


    private val viewBinding by viewBinding(ScreenExploreBinding::bind)
    private val viewModel by viewModels<ExploreViewModelImpl>()

    @Inject
    lateinit var adabter: ExploreAdabter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getBooksByCategory(Constants.categoryList)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adabter.setClickListener {
            findNavController().navigate(
                ExploreScreenDirections.actionExploreScreenToAboutBookScreen(it)
            )
        }


        viewModel.booksData.observe(viewLifecycleOwner) {
            adabter.setData(it)
        }

        viewModel.errorData.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        viewBinding.apply {
            recycler.layoutManager = LinearLayoutManager(requireContext())
            recycler.adapter = adabter
        }

    }


}
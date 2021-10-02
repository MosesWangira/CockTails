package com.mosesaltruism.cocktails.presentation.byname.views

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.mosesaltruism.cocktails.R
import com.mosesaltruism.cocktails.core.common.base.BaseFragment
import com.mosesaltruism.cocktails.core.common.helper.DataStorePreference
import com.mosesaltruism.cocktails.core.common.util.EventStates
import com.mosesaltruism.cocktails.data.remote.asDatabaseModel
import com.mosesaltruism.cocktails.databinding.HomeBinding
import com.mosesaltruism.cocktails.domain.byname.entities.search.SearchedCockTailItem
import com.mosesaltruism.cocktails.presentation.byname.adapters.SearchAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@AndroidEntryPoint
class Home : BaseFragment<HomeBinding>() {
    override fun getFragmentView(): Int = R.layout.home
    private val viewModel: HomeViewModel by viewModels()
    private var searchAdapter: SearchAdapter? = null

    @Inject
    lateinit var preferences: DataStorePreference

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cockTailName = runBlocking { preferences.searchedCocktailName.first() }

        viewModel.loadCockTails(cockTailName ?: "gin")
        getRemoteSearchedCockTail()

        collectCockTailsDB()
    }

    //collects cocktails from database
    private fun collectCockTailsDB(){
       viewLifecycleOwner.lifecycleScope.launch {
           viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
               viewModel.getSearchedCockTails.collect {
                   showLocalSearchedCockTails(it)
               }
           }
       }
    }

    private fun showLocalSearchedCockTails(itemList: List<SearchedCockTailItem>){
        searchAdapter = SearchAdapter()
        searchAdapter?.baseItems = itemList
        binding.cockTailsRecyclerSearched.adapter = searchAdapter
    }


    private fun getRemoteSearchedCockTail() {
        // Create a new coroutine
        viewLifecycleOwner.lifecycleScope.launch {
            //automatically restarts the block when the lifecycle is STARTED again
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                // Safely collect from searchCockTailFlow when the lifecycle is STARTED
                // and stops collection when the lifecycle is STOPPED
                viewModel.searchList.collect {
                    when (it) {
                        is EventStates.Success -> {
                            binding.cockTailsRecyclerSearched.visibility = VISIBLE
                            viewModel.apply {
                                deleteAndInsertNewCockTails(it.successResponse.asDatabaseModel())
                            }
                        }
                        is EventStates.Failure -> {
                            binding.cockTailsRecyclerSearched.visibility = VISIBLE
                        }
                        is EventStates.Loading -> {
                           binding.cockTailsRecyclerSearched.visibility = GONE
                        }

                        is EventStates.Empty -> {
                            Toast.makeText(requireContext(), "empty", Toast.LENGTH_SHORT).show()
                        }
                        else -> Unit
                    }
                }
            }
        }
    }

}
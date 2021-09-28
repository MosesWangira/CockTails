package com.mosesaltruism.cocktails.presentation.byname

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.mosesaltruism.cocktails.R
import com.mosesaltruism.cocktails.core.common.base.BaseFragment
import com.mosesaltruism.cocktails.core.common.util.EventStates
import com.mosesaltruism.cocktails.data.remote.asDatabaseModel
import com.mosesaltruism.cocktails.databinding.HomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class Home : BaseFragment<HomeBinding>() {
    override fun getFragmentView(): Int = R.layout.home
    private val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadCockTails("gin")
        showSearchedCockTail()

        collectCockTailsDB()
    }

    //collects cocktails from database
    private fun collectCockTailsDB(){
       viewLifecycleOwner.lifecycleScope.launch {
           viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
               viewModel.getSearchedCockTails.collect {
                   binding.tester.text = it.toString()
               }
           }
       }
    }

    private fun showSearchedCockTail() {
        // Create a new coroutine
        viewLifecycleOwner.lifecycleScope.launch {
            //automatically restarts the block when the lifecycle is STARTED again
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                // Safely collect from locationFlow when the lifecycle is STARTED
                // and stops collection when the lifecycle is STOPPED
                viewModel.searchList.collect {
                    when (it) {
                        is EventStates.Success -> {
                            viewModel.insertSearchedCockTail(it.successResponse.asDatabaseModel())
                        }
                        is EventStates.Failure -> {

                        }
                        is EventStates.Loading -> {

                        }
                        else -> Unit
                    }
                }
            }
        }
    }

}
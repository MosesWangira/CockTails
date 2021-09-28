package com.mosesaltruism.cocktails.presentation.byname

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mosesaltruism.cocktails.core.common.helper.DataStorePreference
import com.mosesaltruism.cocktails.core.common.util.DispatcherProvider
import com.mosesaltruism.cocktails.core.common.util.EventStates
import com.mosesaltruism.cocktails.data.remote.NetworkSearchContainer
import com.mosesaltruism.cocktails.data.repository.SearchRepository
import com.mosesaltruism.cocktails.domain.byname.entities.search.SearchedCockTailItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: SearchRepository,
    private val dispatchers: DispatcherProvider,
    private val preferences: DataStorePreference
) : ViewModel(), LifecycleObserver {

    //from DB
    var getSearchedCockTails  = repository.getCockTailsSearched


    private val _searchList = MutableStateFlow<EventStates<NetworkSearchContainer>>(
        EventStates.Empty
    )
    val searchList: StateFlow<EventStates<NetworkSearchContainer>> = _searchList


    fun loadCockTails(cockTailName: String) {
        viewModelScope.launch(dispatchers.io) {
            _searchList.value = EventStates.Loading
            _searchList.value = repository.getSearchResponse(cockTailName)
        }
    }

    fun insertSearchedCockTail(cocktails: List<SearchedCockTailItem>) = viewModelScope.launch {
        repository.insertCockTailToDB(cocktails)
    }
}
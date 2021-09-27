package com.mosesaltruism.cocktails.presentation.byname

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mosesaltruism.cocktails.core.common.helper.DataStorePreference
import com.mosesaltruism.cocktails.core.common.util.DispatcherProvider
import com.mosesaltruism.cocktails.core.common.util.EventStates
import com.mosesaltruism.cocktails.data.model.search.Drinks
//import com.mosesaltruism.cocktails.data.model.search.Drink
//import com.mosesaltruism.cocktails.data.model.search.SearchCockTail
import com.mosesaltruism.cocktails.data.remote.NetworkSearchContainer
import com.mosesaltruism.cocktails.data.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: SearchRepository,
    private val dispatchers: DispatcherProvider,
    private val preferences: DataStorePreference
) : ViewModel(), LifecycleObserver {

    private val searchedName = runBlocking { preferences.searchedCocktailName.first() ?: "gin" }

    init {
        loadCockTailsRoom()
    }

    val loadList: Flow<List<Drinks>> = repository.cockTailsSearched


    private fun loadCockTailsRoom(cockTailName: String = " gin"){
        viewModelScope.launch(dispatchers.io) {
            try {
                repository.getSearchedCockTails(cockTailName)
            }catch (e: Exception){
                Timber.d("CockTailsException : $e")
            }
        }
    }






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
}
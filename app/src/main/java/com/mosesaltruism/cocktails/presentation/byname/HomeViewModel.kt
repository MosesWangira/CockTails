package com.mosesaltruism.cocktails.presentation.byname

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mosesaltruism.cocktails.core.common.util.DispatcherProvider
import com.mosesaltruism.cocktails.core.common.util.EventStates
import com.mosesaltruism.cocktails.data.model.home.SearchCockTail
import com.mosesaltruism.cocktails.data.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: SearchRepository,
    private val dispatchers: DispatcherProvider
) : ViewModel(), LifecycleObserver {
    private val _searchList = MutableStateFlow<EventStates<Response<SearchCockTail>>>(
        EventStates.Empty
    )
    val searchList: StateFlow<EventStates<Response<SearchCockTail>>> = _searchList

    fun loadCockTails(cockTailName: String) {
        viewModelScope.launch(dispatchers.io) {
            _searchList.value = EventStates.Loading
            _searchList.value = repository.getSearchResponse(cockTailName)
        }
    }
}
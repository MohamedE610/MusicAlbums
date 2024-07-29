package com.musicalbums.features.topalbums.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.musicalbums.core.exception.AppException
import com.musicalbums.core.exception.toAppException
import com.musicalbums.features.topalbums.domain.entity.Album
import com.musicalbums.features.topalbums.domain.usecase.GetAlbumUseCase
import com.musicalbums.features.topalbums.domain.usecase.GetTopAlbumsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AlbumsViewModel @Inject constructor(
    private val getTopAlbumsUseCase: GetTopAlbumsUseCase,
    private val getAlbumUseCase: GetAlbumUseCase
) : ViewModel() {
    private val _state: MutableStateFlow<AlbumsState> by lazy { MutableStateFlow(AlbumsState.Initial) }
    val state = _state

    init {
        loadTopAlbums()
    }

    fun loadTopAlbums() {
        _state.value = AlbumsState.Loading
        getTopAlbumsUseCase()
            .onEach { _state.value = AlbumsState.Success(it) }
            .catch { _state.value = AlbumsState.Error(it.toAppException()) }
            .launchIn(viewModelScope)
    }

    fun getAlbum(id: String) = getAlbumUseCase(id)
}

sealed class AlbumsState {
    data object Initial : AlbumsState()
    data object Loading : AlbumsState()
    data class Success(val data: List<Album>) : AlbumsState()
    data class Error(val e: AppException) : AlbumsState()
}
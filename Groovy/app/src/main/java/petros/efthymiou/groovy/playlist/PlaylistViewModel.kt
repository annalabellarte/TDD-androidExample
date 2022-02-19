package petros.efthymiou.groovy.playlist

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PlaylistViewModel @Inject constructor(
    private val repository: PlaylistRepository
) : ViewModel(){

    val loading = MutableLiveData<Boolean>()

    val playlists = liveData<Result<List<Playlist>>> {
        loading.postValue(true)
        emitSource(repository.getPlaylists()
            .onEach { loading.postValue(false) }
            .asLiveData())
    }

}

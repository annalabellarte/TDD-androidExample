package petros.efthymiou.groovy.playlist

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class PlaylistService(
    private val playlistAPI: PlaylistAPI
) {

    suspend fun fetchPlaylists() : Flow<Result<List<Playlist>>> = flow{
        emit(Result.success(playlistAPI.fetchAllPlaylists()))
    }.catch { error ->
        emit(Result.failure(error))
    }
}

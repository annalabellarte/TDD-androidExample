package petros.efthymiou.groovy.playlist

import petros.efthymiou.groovy.R
import javax.inject.Inject

class PlaylistMapper @Inject constructor(): Function1<List<PlaylistRaw>, List<Playlist>>{
    override fun invoke(playlist: List<PlaylistRaw>): List<Playlist> {
        return playlist.map {
            val image = if(it.category == "rock") R.mipmap.rock else R.mipmap.playlist
            return@map Playlist(
                it.id,
                it.name,
                it.category,
                image
            )
        }
    }
}
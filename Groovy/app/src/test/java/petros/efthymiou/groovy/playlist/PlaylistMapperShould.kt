package petros.efthymiou.groovy.playlist

import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.assertEquals
import org.junit.Test
import petros.efthymiou.groovy.R
import petros.efthymiou.groovy.utils.BaseUnitTest

class PlaylistMapperShould: BaseUnitTest() {
    private val mapper = PlaylistMapper()
    private val playlistRaw = PlaylistRaw("1", "Name", "Category")
    private val playlistRockRaw = PlaylistRaw("1", "Name", "rock")

    private val playlists = mapper(listOf(playlistRaw, playlistRockRaw))
    private val playlist = playlists.first()
    private val playlistRock = playlists.last()

    @Test
    fun keepSameId(){
        assertEquals(playlistRaw.id, playlist.id)
    }

    @Test
    fun keepSameName(){
        assertEquals(playlistRaw.name, playlist.name)
    }

    @Test
    fun keepSameCategory(){
        assertEquals(playlistRaw.category, playlist.category)
    }

    @Test
    fun mapDefaultImageIfNotRockCategory(){
        assertEquals(R.mipmap.playlist, playlist.image)
    }

    @Test
    fun mapRockImageIfRockCategory(){
        assertEquals(R.mipmap.rock, playlistRock.image)
    }
}
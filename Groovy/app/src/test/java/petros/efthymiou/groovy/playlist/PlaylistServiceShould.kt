package petros.efthymiou.groovy.playlist

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import petros.efthymiou.groovy.utils.BaseUnitTest

class PlaylistServiceShould: BaseUnitTest() {
    private val api: PlaylistAPI = mock()
    private val playlists = mock<List<PlaylistRaw>>()
    private val exception = RuntimeException("Si è verificato un errore")

    @Test
    fun getPlaylistFromApi() = runBlockingTest {

        val service = PlaylistService(api)
        service.fetchPlaylists().first()

        verify(api, times(1)).fetchAllPlaylists()
    }

    @Test
    fun convertValuesToFlowResultAndEmitThem() = runBlockingTest {
        val service = mockSuccessfulCase()
        assertEquals(playlists, service.fetchPlaylists().first().getOrNull())
    }

    @Test
    fun convertErrorToFLowResultAndEmitIt() = runBlockingTest {
        val service = mockFailureCase()

        assertEquals(exception, service.fetchPlaylists().first().exceptionOrNull())
    }

    private suspend fun mockSuccessfulCase(): PlaylistService {
        whenever(api.fetchAllPlaylists()).thenReturn(
            playlists
        )
        return PlaylistService(api)
    }

    private suspend fun mockFailureCase(): PlaylistService {
        whenever(api.fetchAllPlaylists()).thenThrow(exception)
        val service = PlaylistService(api)
        return service
    }
}
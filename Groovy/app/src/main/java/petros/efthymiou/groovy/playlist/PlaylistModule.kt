package petros.efthymiou.groovy.playlist

import com.jakewharton.espresso.OkHttp3IdlingResource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private val client = OkHttpClient()
val idlingResource = OkHttp3IdlingResource.create("OkHttp", client)

@Module
@InstallIn(SingletonComponent::class)
class PlaylistModule {

    @Provides
    fun getPlaylistAPI(retrofit: Retrofit): PlaylistAPI{
        return retrofit.create(PlaylistAPI::class.java)
    }

    @Provides
    @Singleton
    fun getRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("http://192.168.1.160:3000/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}


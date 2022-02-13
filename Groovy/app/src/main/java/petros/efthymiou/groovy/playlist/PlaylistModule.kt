package petros.efthymiou.groovy.playlist

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(FragmentComponent::class)
class PlaylistModule {

    @Provides
    fun getPlaylistAPI(retrofit: Retrofit): PlaylistAPI{
        return retrofit.create(PlaylistAPI::class.java)
    }

    @Provides
    fun getRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("http://192.168.1.160:3000/")
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
package com.example.desafiowebservicesdigitalhouse.api

import com.example.desafiowebservicesdigitalhouse.data.CharactersResponseWrapper
import com.example.desafiowebservicesdigitalhouse.data.ComicsResponseWrapper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val PUBLIC_KEY = "c70e158b477c5ee33c838850e91a0be0"
private const val HASH = "81355df70f12c476ba862f710a25f8c8"
private const val TS = "3"

/**
 * Used to connect to the Marvel Comics API
 */
interface MarvelComicsService {

    @GET("characters")
    suspend fun getCharacterData(
        @Query("name") nameStartsWith: String = "Spider-Man",
        @Query("apikey") apikey: String = PUBLIC_KEY,
        @Query("hash") hash: String = HASH,
        @Query("ts") ts: String = TS
    ): CharactersResponseWrapper

    @GET("characters/{characterId}/comics")
    suspend fun getComicDataByCharacterId(
        @Path("characterId") characterId: Int,
        @Query("orderBy") orderBy: String = "-issueNumber",
        @Query("apikey") apikey: String = PUBLIC_KEY,
        @Query("hash") hash: String = HASH,
        @Query("ts") ts: String = TS
    ): ComicsResponseWrapper

    companion object {
        private const val BASE_URL = "https://gateway.marvel.com/v1/public/"

        fun create(): MarvelComicsService {
            val logger =
                HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MarvelComicsService::class.java)
        }
    }

}
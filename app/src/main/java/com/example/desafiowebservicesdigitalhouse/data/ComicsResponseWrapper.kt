package com.example.desafiowebservicesdigitalhouse.data


import com.google.gson.annotations.SerializedName
import java.io.Serializable


/**
 * Data class that represents a response from Marvel Comics API fetching a list of comics from a
 * specific character.
 *
 */
data class ComicsResponseWrapper(
    val `data`: Data,
) {
    data class Data(
        val count: Int,
        val limit: Int,
        val offset: Int,
        val results: List<Result>,
        val total: Int
    ) {
        data class Result(
            val dates: List<Date>,
            val description: String?,
            val id: Int,
            val issueNumber: Float,
            val pageCount: Int,
            val prices: List<Price>,
            val thumbnail: Thumbnail,
            val title: String,
        ) : Serializable {

            data class Date(
                val date: String,
                val type: String
            )

            data class Price(
                val price: Double,
                val type: String
            )

            data class Thumbnail(
                val extension: String,
                val path: String
            )

        }
    }
}
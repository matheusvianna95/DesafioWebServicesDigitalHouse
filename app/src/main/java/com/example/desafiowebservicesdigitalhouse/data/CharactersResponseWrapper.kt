package com.example.desafiowebservicesdigitalhouse.data

/**
 * Data class that represents a response from Marvel Comics API fetching a list of characters
 * matching query parameters.
 *
 * Currently this class is not being used, nevertheless, it was included in the project in case new
 * features (such a querying by character) are added in the future.
 *
 */
data class CharactersResponseWrapper(
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
            val comics: Comics,
            val description: String,
            val id: Int,
            val modified: String,
            val name: String,
            val resourceURI: String,
        ) {
            data class Comics(
                val available: Int,
                val collectionURI: String,
                val items: List<Item>,
                val returned: Int
            ) {
                data class Item(
                    val name: String,
                    val resourceURI: String
                )
            }
        }
    }
}
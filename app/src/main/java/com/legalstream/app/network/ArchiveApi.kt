package com.legalstream.app.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ArchiveApi {
    @GET("advancedsearch.php")
    suspend fun searchMovies(
        @Query("q") query: String = "collection:(prelinger) AND mediatype:(movies)",
        @Query("fl[]") fields: Array<String> = arrayOf("identifier", "title", "description"),
        @Query("rows") rows: Int = 30,
        @Query("page") page: Int = 1,
        @Query("output") output: String = "json"
    ): Response<SearchResponse>
}

data class SearchResponse(
    val response: ResponseData
)

data class ResponseData(
    val docs: List<MovieDoc>
)

data class MovieDoc(
    val identifier: String,
    val title: String,
    val description: ArrayList<String>? = null
)

package uz.lycr.lesson24flowpagination.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import uz.lycr.lesson24flowpagination.models.UnsplashImage

interface ApiService {

    @GET("search/photos")
    fun searchUnsplashImage(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = 20,
        @Query("order_by") orderBy: String = "relevant",
        @Query("client_id") clientId: String = "9me90We4E8D15Ln1SdZU0P1i0hETri7ZDUt__sIOrtU"
    ): Call<UnsplashImage>

}
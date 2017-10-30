package ru.arink_group.deliveryapp.data.net.api

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import ru.arink_group.deliveryapp.App
import ru.arink_group.deliveryapp.data.net.response.CategoriesObject
import ru.arink_group.deliveryapp.data.net.response.ProductsObject
import ru.arink_group.deliveryapp.domain.dto.AccountDTO
import ru.arink_group.deliveryapp.domain.dto.AddressDTO
import ru.arink_group.deliveryapp.domain.dto.ProductDTO

/**
 * Created by kirillvs on 20.10.17.
 */
interface BookingFoodApi {

    @GET("api/categories")
    fun categories(@Query("company_id") companyId: String): Observable<CategoriesObject>

    @GET("api/products")
    fun products(@Query("company_id") companyId: String, @Query("category_id") categoryId: String): Observable<ProductsObject>

    @GET("api/products/{product_id}")
    fun product(@Path("product_id") productId: String): Observable<ProductDTO>

    @POST("/api/accounts")
    fun createAccount(@Body account: AccountDTO) : Observable<AccountDTO>

    @POST("/api/accounts/1111/update")
    fun updateAccount(@Body account: AccountDTO) : Observable<AccountDTO>

    @POST("/api/accounts/1111/addresses")
    fun addAddress(@Body address: AddressDTO) : Observable<AddressDTO>

    @PATCH("/api/accounts/1111/addresses/{id}")
    fun updateAddress(@Path("id") addressId: String, @Body address: AddressDTO) : Observable<AddressDTO>

    @DELETE("/api/accounts/1111/addresses/{id}")
    fun deleteAddress(@Path("id") addressId: String) : Observable<Void>

    @GET("/api/accounts/1111")
    fun getAccount(): Observable<AccountDTO>

    // TODO rework, there is no need for 1111

    /**
     * Companion object to create the BoolingFoodApi
     */
    companion object Factory {
        fun create(): BookingFoodApi {
            val client = OkHttpClient().newBuilder().addInterceptor {
                chain: Interceptor.Chain ->
                var request = chain.request()
                val url = request.url().newBuilder().addQueryParameter("uuid", App.getUUID()).build()
                request = request.newBuilder().url(url).build()
                return@addInterceptor chain.proceed(request)
            }.build()

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("http://23.101.67.216:8080/")
                    .client(client)
                    .build()

            return retrofit.create(BookingFoodApi::class.java);
        }
    }
}

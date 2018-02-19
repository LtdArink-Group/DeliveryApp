package ru.arink_group.deliveryapp.data.net.api

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import ru.arink_group.deliveryapp.App
import ru.arink_group.deliveryapp.BuildConfig
import ru.arink_group.deliveryapp.data.net.response.CategoriesObject
import ru.arink_group.deliveryapp.data.net.response.OrdersObject
import ru.arink_group.deliveryapp.data.net.response.ProductsObject
import ru.arink_group.deliveryapp.domain.dto.*

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

    @POST("/api/accounts/{accountId}/update")
    fun updateAccount(@Path("accountId") accountId: String, @Body account: AccountDTO) : Observable<AccountDTO>

    @POST("/api/accounts/{accountId}/addresses")
    fun addAddress(@Path("accountId") accountId: String, @Body address: AddressDTO) : Observable<AddressDTO>

    @POST("/api/accounts/{accountId}/addresses/{addressId}")
    fun updateAddress(@Path("addressId") addressId: String, @Path("accountId") accountId: String, @Body address: AddressDTO) : Observable<AddressDTO>

    @PATCH("/api/accounts/{accountId}/addresses/{addressId}")
    fun updateAddressPatch(@Path("addressId") addressId: String, @Path("accountId") accountId: String, @Body address: AddressDTO) : Observable<Void?>

    @DELETE("/api/accounts/{accountId}/addresses/{addressId}")
    fun deleteAddress(@Path("addressId") addressId: String, @Path("accountId") accountId: String) : Observable<Void>

    @GET("/api/accounts/{accountId}")
    fun getAccount(@Path("accountId") accountId: String): Observable<AccountDTO>

    @GET("/api/companies/{companyId}")
    fun getCompany(@Path("companyId") companyId: String) : Observable<CompanyDTO>

    @POST("/api/orders")
    fun sendOrder(@Body order: OrderDTO) : Observable<Any>

    @GET("/api/orders")
    fun getOrders(@Query("account_id") accountId: String) : Observable<OrdersObject>

    @POST("/api/orders/{orderId}/cancel")
    fun cancelOrder(@Path("orderId") orderId: String) : Observable<Any>

    /**
     * Companion object to create the BoolingFoodApi
     */
    companion object Factory {
        fun create(): BookingFoodApi {
            val url = BuildConfig.SERVER_URL

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(url)
                    .build()

            return retrofit.create(BookingFoodApi::class.java);
        }
    }
}

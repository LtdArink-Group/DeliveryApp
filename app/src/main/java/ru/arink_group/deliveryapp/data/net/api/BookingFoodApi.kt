package ru.arink_group.deliveryapp.data.net.api

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.arink_group.deliveryapp.data.net.response.CategoriesObject
import ru.arink_group.deliveryapp.data.net.response.ProductsObject
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

    /**
     * Companion object to create the BoolingFoodApi
     */
    companion object Factory {
        fun create(): BookingFoodApi {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("http://23.101.67.216:8080/")
                    .build()

            return retrofit.create(BookingFoodApi::class.java);
        }
    }
}

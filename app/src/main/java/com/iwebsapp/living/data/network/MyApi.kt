package com.iwebsapp.living.data.network

import com.iwebsapp.living.BuildConfig
import com.iwebsapp.living.data.network.responses.AuthResponse
import com.iwebsapp.living.data.network.responses.ServiceResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface MyApi {

    @FormUrlEncoded
    @Headers("User-Agent: Android")
    @POST("user/auth")
    suspend fun loginliving(
        @Field("phone") phone: String,
        @Field("password") password: String
    ): Response<AuthResponse>

    // @GET("service/all/{idSub}")
    //    Call<ServicesModel> getServices(
    //            @Path("idSub") String idSub,
    //            @Header("Authorization") String authHeader
    //    )

    @GET("service/list/all")
    suspend fun getServices(
        @Header("Authorization") authHeader: String
    ) : Response<ServiceResponse>

    companion object{
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ) : MyApi {

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }
}
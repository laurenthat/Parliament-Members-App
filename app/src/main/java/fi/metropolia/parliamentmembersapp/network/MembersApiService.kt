package fi.metropolia.parliamentmembersapp.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

//Date: 10.10.2022
//Laurentiu Sebastian Hategan
//Student id: 2112621
//MembersApiService is a network layer which the viewModel uses to communicate to the internet.

private const val BASE_URL =
    "https://users.metropolia.fi/~peterh/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface MembersApiService {
    @GET("seating.json")
    suspend fun getMembersList(): List<Members>
}

object MemberApi {
    val retrofitService : MembersApiService by lazy {
        retrofit.create(MembersApiService::class.java) }
}
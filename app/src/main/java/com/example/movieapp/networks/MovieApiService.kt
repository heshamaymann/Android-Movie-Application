package com.example.movieapp.networks

import com.example.movieapp.features.fragment.ItemCharacter
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


private const val BASE_URL = "https://rickandmortyapi.com/api/"
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private val okhttpClient = OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor()
    .setLevel(HttpLoggingInterceptor.Level.BODY)).build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory()).client(okhttpClient)
    .baseUrl(BASE_URL)
    .build()
interface MovieApiService{
    @GET ("character")
    fun getChars(): Deferred<CharResponse>
    @GET ("character/{id}")
    fun getCharDetails(@Path("id") id:Int): Deferred<ItemCharacter>
   @GET ("location/{id}")
    fun getCharLoc(@Path("id")id : Int) : Deferred <LocResponse>
}

object MovieApi{
    val retrofitservice : MovieApiService by lazy { retrofit.create(MovieApiService::class.java) }
}


//Keywords
/*
* Progress bar view hide.
* send args to fragment using nav component.
* */
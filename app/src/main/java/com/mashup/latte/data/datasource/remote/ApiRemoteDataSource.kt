package com.mashup.latte.data.datasource.remote

import com.mashup.latte.data.dto.response.DiariesResponse
import com.mashup.latte.data.datasource.local.entity.AlcoholDiary
import com.mashup.latte.data.dto.request.TokenRequest
import com.mashup.latte.data.dto.response.TokenResponse
import com.mashup.latte.data.repository.ApiRepository
import com.mashup.latte.ext.e
import com.mashup.latte.util.FileManager
import com.mashup.latte.util.NetworkManager
import io.reactivex.Single
import okhttp3.MultipartBody
import org.koin.core.KoinComponent
import retrofit2.Call
import java.io.File

/**
 * Created by Namget on 2020.02.15.
 */
class ApiRemoteDataSource(
    private val apiService: ApiService,
    private val networkManager: NetworkManager,
    private val fileManager: FileManager
) : ApiRepository, KoinComponent {


    override fun getLoginToken(tokenRequest: TokenRequest): Single<TokenResponse> =
        apiService.getLoginToken(tokenRequest)


    override fun insertAlcoholDiary(alcoholDiary: AlcoholDiary) {
        throw IllegalAccessException("local api not supported")
//        val paths = alcoholDiary.imagePath
//        e("test", "paths${paths!![0]}")
//        val files: MutableList<File> = mutableListOf()
//        val multipartFile: MutableList<MultipartBody.Part> = mutableListOf()
//        for (index in 0 until ((paths?.size) ?: 0)) {
//            multipartFile.add(
//                index,
//                networkManager.buildMultipartWithFile(File(paths!![index]), "file")
//            )
//        }
        apiService.postDiary(alcoholDiary.toAlcoholRequest()).execute()
    }

    override fun getDiaries(): Single<List<AlcoholDiary>> {
        throw IllegalAccessException("local api not supported")
    }
}
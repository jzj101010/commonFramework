package com.jjz.common.http

import com.google.gson.JsonObject
import com.jjz.common.http.result.AccessTokenBean
import com.jjz.common.http.result.HttpResult
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.*


interface ApiService {

    //获取 token
    @POST
    @FormUrlEncoded
    fun getToken(
        @Url path: String
        , @Field("grant_type") grantType: String
        , @Field("username") username: String
        , @Field("password") password: String
    ): Observable<AccessTokenBean>

    //刷新 token
    @POST
    @FormUrlEncoded
    fun refreshToken(
        @Url path: String
        , @Field("grant_type") grantType: String
        , @Field("refresh_token") refreshToken: String
    ):Observable<AccessTokenBean>

    @GET
    fun getData(@Url path: String, @QueryMap map: Map<String, String>): Observable<HttpResult>

    @GET
    fun getData(@Url path: String): Observable<HttpResult>


    @GET
    fun getListData(@Url path: String, @QueryMap map: Map<String, String>): Observable<HttpResult>

    /**
     * 下载文件
     * 如视频
     * Streaming 大文件时要加不然会OOM
     * @param fileUrl
     * @return
     */
    @Streaming
    @GET
    fun downloadFile(@Url fileUrl: String): Observable<ResponseBody>


    @GET
    fun getListData(@Url path: String, @QueryMap map: Map<String, String>, @Query("sort") list: List<String>): Observable<HttpResult>

    @PATCH
    fun patchBodyData(@Url path: String, @Body any: Any): Observable<HttpResult>


    @POST
    @FormUrlEncoded
    fun postFormData(@Url path: String, @FieldMap map: Map<String, String>): Observable<HttpResult>

    @POST
    @FormUrlEncoded
    fun postFormListData(@Url path: String, @FieldMap map: Map<String, String>): Observable<HttpResult>

    @POST
    fun postBodyData(@Url path: String, @Body jsonObject: JsonObject): Observable<HttpResult>




    @PATCH
    fun patchBodyData(@Url path: String, @Body jsonObject: JsonObject): Observable<HttpResult>


    @PUT
    fun putBodyData(@Url path: String, @Body jsonObject: JsonObject): Observable<HttpResult>


    @PUT
    @FormUrlEncoded
    fun putData(@Url path: String, @FieldMap map: Map<String, String>): Observable<HttpResult>


    @DELETE
    fun patchBodyData(@Url path: String): Observable<HttpResult>



    @POST("{path}")
    fun postBodyData(
        @Path(
            "path",
            encoded = true
        ) path: String, @Body any: Any
    ): Observable<HttpResult>


    @POST("{path}")
    fun postBodyData(
        @Path(
            "path",
            encoded = true
        ) path: String, @Query("studentIds") ids: Array<Int>, @Body any: Any
    ): Observable<HttpResult>


    @Multipart
    @POST
    fun postFileList(@Url path: String, @Part files: List<MultipartBody.Part>, @PartMap requestBody: @JvmSuppressWildcards Map<String, RequestBody>): Observable<HttpResult>



}
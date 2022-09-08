package com.example.contadoocuotas

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Url

interface APIService {
    @Headers("Authorization: BEARER eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2OTQwMjU5MDYsInR5cGUiOiJleHRlcm5hbCIsInVzZXIiOiJpZ25hY2lvdG9tYXMuZGFAZ21haWwuY29tIn0.B2-CAnrAmHZbOUF18253qY57FF8aW1p58nluXQJJs5QhPMC6y8R49S5NhkGxmMqmzxAqKpwnyr11ZpDtRZ4pEg")
    @GET
    fun getInflacionMensual(@Url url: String): Response<BcraResponse>
}

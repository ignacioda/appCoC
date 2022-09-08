package com.example.contadoocuotas

import com.google.gson.annotations.SerializedName

data class BcraResponse(@SerializedName("d")var fecha: List<String>,
@SerializedName("v") var inflacion: List<String> )

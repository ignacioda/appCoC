package com.example.contadoocuotas

import android.annotation.SuppressLint

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contadoocuotas.adapter.CuotasAdapter
import com.example.contadoocuotas.databinding.ActivityMain2Binding
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.pow

private lateinit var  binding: ActivityMain2Binding

var precioCuota: Double = 0.0
var tasaInflacionMensual: Double =6.5
var sumatoria: Double = 0.0
var vCont: Double =0.0
var vCuotas: Double =0.0
var nCuotas: Int = 0
var cuota = 0.0
val df= DecimalFormat("#.##")
val listaCuotas: MutableList<DCCuotas> = mutableListOf()




class MainActivity2 : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)


        df.roundingMode = RoundingMode.DOWN

        vCont = intent.getDoubleExtra("cuotas",0.0)
        vCuotas = intent.getDoubleExtra("cuotas", 0.0)
        nCuotas = intent.getIntExtra("cantc", 0)


        binding.buttonVolver.setOnClickListener {
            precioCuota =0.0
            sumatoria = 0.0
            finish()
            listaCuotas.clear()
        }

        ajusteCuotasInflacion()
        conveniencia()
        initReciclerView()
        }


    fun ajusteCuotasInflacion(){
        precioCuota = (vCuotas/nCuotas)

        for (i in 1..nCuotas)  {

            cuota = precioCuota/((1+(tasaInflacionMensual/100)).pow(i))

            listaCuotas.add(
                DCCuotas(i, cuota, precioCuota)
            )

            sumatoria += cuota

        }
    }

    private fun initReciclerView(){
        val reciclerView = binding.rvCuotas
        reciclerView.layoutManager = LinearLayoutManager(this)
        reciclerView.adapter=CuotasAdapter(listaCuotas)

    }

    @SuppressLint("SetTextI18n")
    fun conveniencia(){


        if (vCont<sumatoria){
            binding.tvHeader.text="Conviene en Contado"

            binding.tvConveniencia.text="En contado $vCont y en cuotas pagas ${df.format(sumatoria)}"
        } else {
            binding.tvHeader.text= "Conviene en Cuotas"

            binding.tvConveniencia.text = "En cuotas pagas ${df.format(sumatoria)} y en contado ${vCont}"
        }


            }



}






package com.example.contadoocuotas.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.contadoocuotas.DCCuotas
import com.example.contadoocuotas.R
import com.example.contadoocuotas.databinding.ActivityMain2Binding
import java.math.RoundingMode
import java.text.DecimalFormat


class CuotasViewHolder(view: View): RecyclerView.ViewHolder(view) {



    private val tvcuota = view.findViewById<TextView>(R.id.tv_cuota)
    private val tvmonto = view.findViewById<TextView>(R.id.tv_monto)

    fun render(dcCuotas: DCCuotas) {

        val df= DecimalFormat("#.##")
        df.roundingMode = RoundingMode.DOWN

        tvcuota.text = "Cuota n° "+dcCuotas.nCuota.toString()
        tvmonto.text= "$ "+df.format(dcCuotas.precioCuota)
    }
}
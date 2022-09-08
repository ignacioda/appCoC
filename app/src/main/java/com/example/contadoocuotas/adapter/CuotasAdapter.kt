package com.example.contadoocuotas.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contadoocuotas.DCCuotas
import com.example.contadoocuotas.R

class CuotasAdapter(private val listaCuotas: MutableList<DCCuotas>): RecyclerView.Adapter<CuotasViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CuotasViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CuotasViewHolder(layoutInflater.inflate(R.layout.item_cuota,parent, false))
    }

    override fun onBindViewHolder(holder: CuotasViewHolder, position: Int) {
       val item = listaCuotas[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = listaCuotas.size

}
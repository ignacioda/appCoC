package com.example.contadoocuotas

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import com.example.contadoocuotas.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.Serializable
import java.util.*
import java.util.Calendar.getInstance as getInstance


class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener, Serializable {

    private lateinit var mBinding: ActivityMainBinding

    private lateinit var aaCuotas: ArrayAdapter<String>
    var valorContado: Double =0.0
    var valorCuotas: Double = 0.0
    var numeroCuotas: Int = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        supportActionBar?.hide()


        //callBcra()
        val fechaHoy =getCurrentDateTime().toString()
        mBinding.textView3.text="Inflación Agosto 2022 6,5%"

        //Spinner
        aaCuotas = ArrayAdapter<String>(this,
            android.R.layout.simple_spinner_dropdown_item)
        mBinding.spinner.adapter = aaCuotas
        aaCuotas.addAll(Arrays.asList("Seleccione","3 Cuotas","6 Cuotas","12 Cuotas","24 Cuotas",
        "36 Cuotas", "48 Cuotas"))
        mBinding.spinner.onItemSelectedListener = this


        mBinding.buttonCalc.setOnClickListener {
            valorContado= mBinding.inputContado.text.toString().toDouble()
            valorCuotas = mBinding.inputContado.text.toString().toDouble()

            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("cuotas",valorCuotas.toString().toDouble())
            intent.putExtra("cont", valorContado.toString().toDouble())
            intent.putExtra("cantc", numeroCuotas)
            startActivity(intent)
        }


    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.estadisticasbcra.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun callBcra(){
        CoroutineScope(Dispatchers.IO).launch{
            val fechaHoy =getCurrentDateTime().toString()
            val call = getRetrofit().create(APIService::class.java).getInflacionMensual("inflacion_mensual_oficial")
            val resp: BcraResponse? = call.body()

            runOnUiThread {
                if (call.isSuccessful){
                    //val c = Calendar.getInstance()
                   // val mesHoy = c.get(Calendar.MONTH)
                    mBinding.textView3.text = "$resp  "
                }else{
                    showError()
                }

            }


        }
    }

    private fun showError() {
        Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show()
    }

    fun getCurrentDateTime(): Date {
        return getInstance().time
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
        hideKeyboard()
        if (position==0){
            mBinding.buttonCalc.isClickable =false
            mBinding.buttonCalc.setBackgroundColor(Color.GRAY)
        } else {
            mBinding.buttonCalc.isClickable =true
            mBinding.buttonCalc.setBackgroundColor(Color.GREEN)
        }
        if(position==1){
            numeroCuotas =3
        }
        if(position==2){
            numeroCuotas=6
        }
        if(position==3){
            numeroCuotas=12
        }
        if(position==4){
            numeroCuotas=24
        }
        if(position==5){
            numeroCuotas=36
        }
        if(position==6){
            numeroCuotas=48
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        hideKeyboard()
        mBinding.buttonCalc.isClickable = false
    }

    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(mBinding.viewRoot.windowToken, 0)
    }



}







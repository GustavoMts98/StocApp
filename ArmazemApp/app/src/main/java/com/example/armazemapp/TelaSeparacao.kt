package com.example.armazemapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.zxing.integration.android.IntentIntegrator

class TelaSeparacao : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_separacao)


        val txtProduto = intent
        val parametros = txtProduto.extras
        val produtoASeparar = parametros!!.getString("produto")
        val produtoASepararArray = produtoASeparar?.split("|")


        val btnScanearRua: Button = findViewById(R.id.btnScanearRua)
        btnScanearRua.setOnClickListener {
            val integrator: IntentIntegrator = IntentIntegrator(this)
            integrator.setPrompt("Leitura de QRCode")
            obterResultadoRua.launch(integrator.createScanIntent())
        }

        val btnScanearPredio : Button = findViewById(R.id.btnScanearPredio)
        btnScanearPredio.setOnClickListener {
            val integrator: IntentIntegrator = IntentIntegrator(this)
            integrator.setPrompt("Leitura de QRCode")
            obterResultadoPredio.launch(integrator.createScanIntent())
        }

        val btnScanearAndar : Button = findViewById(R.id.btnScanearAndar)
        btnScanearAndar.setOnClickListener {
            val integrator: IntentIntegrator = IntentIntegrator(this)
            integrator.setPrompt("Leitura de QRCode")
            obterResultadoAndar.launch(integrator.createScanIntent())
        }

        val btnScanearCodigo : Button = findViewById(R.id.btnScanearCodigo)
        btnScanearCodigo.setOnClickListener {
            val integrator: IntentIntegrator = IntentIntegrator(this)
            integrator.setPrompt("Leitura de QRCode")
            obterResultadoCodigo.launch(integrator.createScanIntent())
        }
    }

    public val obterResultadoRua = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        val btnScanearRua : Button = findViewById(R.id.btnScanearRua)
        val btnScanearPredio : Button = findViewById(R.id.btnScanearPredio)
        val txtProduto = intent
        val parametros = txtProduto.extras
        val produtoASeparar = parametros!!.getString("produto")
        val produtoASepararArray = produtoASeparar?.split("|")


        val intentResult = IntentIntegrator.parseActivityResult(it.resultCode, it.data)
        val resultadoQr = intentResult.contents.toString()
        val produtoRua = "R"+produtoASepararArray?.get(2).toString()

        if (produtoRua != resultadoQr) {
            Toast.makeText(this, produtoRua , Toast.LENGTH_LONG).show()
            Toast.makeText(this, "Rua incorreta: "+resultadoQr, Toast.LENGTH_LONG).show()
        }
        else {
            btnScanearRua.setVisibility(View.GONE);
            btnScanearPredio.setVisibility(View.VISIBLE);
            Toast.makeText(this, "Rua informada corretamente", Toast.LENGTH_LONG).show()
        }
    }


    public val obterResultadoPredio = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        val btnScanearPredio : Button = findViewById(R.id.btnScanearPredio)
        val btnScanearAndar : Button = findViewById(R.id.btnScanearAndar)
        val txtProduto = intent
        val parametros = txtProduto.extras
        val produtoASeparar = parametros!!.getString("produto")
        val produtoASepararArray = produtoASeparar?.split("|")

        val intentResult = IntentIntegrator.parseActivityResult(it.resultCode, it.data)
        val resultadoQr = intentResult.contents.toString()
        val produtoRua = "R"+produtoASepararArray?.get(2).toString()
        val produtoPredio = produtoRua+"-"+"N"+produtoASepararArray?.get(3).toString()

        if(produtoPredio != resultadoQr){
            Toast.makeText(this, produtoPredio , Toast.LENGTH_LONG).show()
            Toast.makeText(this, "Predio incorreto: "+resultadoQr, Toast.LENGTH_LONG).show()
        }
        else {
            btnScanearPredio.setVisibility(View.GONE);
            btnScanearAndar.setVisibility(View.VISIBLE);
            Toast.makeText(this, produtoPredio , Toast.LENGTH_LONG).show()
            Toast.makeText(this, "Predio informado corretamente: "+resultadoQr, Toast.LENGTH_LONG).show()
        }
    }

    public val obterResultadoAndar = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        val btnScanearAndar : Button = findViewById(R.id.btnScanearAndar)
        val btnScanearCodigo : Button = findViewById(R.id.btnScanearCodigo)
        val txtProduto = intent
        val parametros = txtProduto.extras
        val produtoASeparar = parametros!!.getString("produto")
        val produtoASepararArray = produtoASeparar?.split("|")


        val intentResult = IntentIntegrator.parseActivityResult(it.resultCode, it.data)
        val resultadoQr = intentResult.contents.toString()

        val produtoRua = "R"+produtoASepararArray?.get(2).toString()
        val produtoPredio = "N"+produtoASepararArray?.get(3).toString()
        val produtoAndar = produtoRua+"-"+produtoPredio+"-"+produtoASepararArray?.get(4)+"A"

        if(produtoAndar != resultadoQr){
            Toast.makeText(this, produtoAndar, Toast.LENGTH_LONG).show()
            Toast.makeText(this, "Andar incorreto: "+resultadoQr, Toast.LENGTH_LONG).show()
        }
        else {
            btnScanearAndar.setVisibility(View.GONE);
            btnScanearCodigo.setVisibility(View.VISIBLE);
            Toast.makeText(this, produtoAndar, Toast.LENGTH_LONG).show()
            Toast.makeText(this, "Andar informado corretamente: "+resultadoQr, Toast.LENGTH_LONG).show()
        }
    }

    public val obterResultadoCodigo = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        val btnScanearCodigo : Button = findViewById(R.id.btnScanearCodigo)
        val btnFinalizar : Button = findViewById(R.id.btnFinalizar)
        val txtProduto = intent
        val parametros = txtProduto.extras
        val produtoASeparar = parametros!!.getString("produto")
        val produtoASepararArray = produtoASeparar?.split("|")


        val intentResult = IntentIntegrator.parseActivityResult(it.resultCode, it.data)
        val resultadoQr = intentResult.contents.toString()
        val produtoCodigo = produtoASepararArray?.get(1).toString()

        if(produtoCodigo != resultadoQr){
            Toast.makeText(this, produtoCodigo, Toast.LENGTH_LONG).show()
            Toast.makeText(this, "Código incorreto: "+resultadoQr, Toast.LENGTH_LONG).show()
        }
        else {
            btnScanearCodigo.setVisibility(View.GONE);
            btnFinalizar.setVisibility(View.VISIBLE);
            Toast.makeText(this, produtoCodigo, Toast.LENGTH_LONG).show()
            Toast.makeText(this, "Código informado corretamente: "+resultadoQr, Toast.LENGTH_LONG).show()

            btnFinalizar.setOnClickListener {
               finish()
            }
        }
    }
}
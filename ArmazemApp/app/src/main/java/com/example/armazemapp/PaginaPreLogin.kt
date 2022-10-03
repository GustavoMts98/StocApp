package com.example.armazemapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class PaginaPreLogin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pagina_pre_login)

        val btnAcessar_aplicativo : Button = findViewById(R.id.btnAcessarAplicativo)

        btnAcessar_aplicativo.setOnClickListener{
            IrParaTelaLogin()
        }


    }

    private fun IrParaTelaLogin() {
        val telaLogin = Intent(this,MainActivity::class.java)
        startActivity(telaLogin)
    }
}
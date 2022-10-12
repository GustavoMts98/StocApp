package com.example.armazemapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtUsuario : EditText = findViewById(R.id.edtUsuario)
        val edtSenha : EditText = findViewById(R.id.edtSenha)
        val btnConectar: Button = findViewById(R.id.btnConectar)
        val btnFecharSistema: Button = findViewById(R.id.btnFecharSistema)

        val usuarios = HashMap <String, String>()
        usuarios ["Gustavo"] = "Gustavo|Gustavo Matos|1234"
        usuarios ["Zeruela"] = "Zeruela|Zeruela Matos|1234"
        usuarios ["Pinguinha"] = "Pinguinha|Pinguinha Matos|1234"


        btnConectar.setOnClickListener {
            val strUsuario: String = edtUsuario.text.toString()
            val strSenha: String = edtSenha.text.toString()
            val usuario = usuarios[strUsuario]?.split("|")
            if(usuario?.get(0)?.compareTo(strUsuario) !=0){
                Toast.makeText(applicationContext,"Usuário incorreto. Tente Novamente", Toast.LENGTH_LONG).show()
                edtUsuario.setText("")
            } else if(usuario.get(2)?.compareTo(strSenha) != 0) {
                Toast.makeText(applicationContext,"Senha incorreta. Tente novamente", Toast.LENGTH_LONG).show()
                edtSenha.setText("")
            } else {

                Toast.makeText(applicationContext,"Seja benvindo "+usuario.get(1).toString()+".",
                    Toast.LENGTH_LONG).show()
                AbrirQRCode()
            }

        }

        btnFecharSistema.setOnClickListener {
            FecharSistema()
        }


    }

    private fun AbrirQRCode() {
        val irParaQrCode = Intent(this, TelaQRCode::class.java)
        startActivity(irParaQrCode)
    }

    private fun FecharSistema() {
        val fecharSistema = Intent(this,PaginaPreLogin::class.java)
        startActivity(fecharSistema)
    }
}
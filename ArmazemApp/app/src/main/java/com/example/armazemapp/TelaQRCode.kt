package com.example.armazemapp

import com.example.armazemapp.Produto
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.net.Uri
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.zxing.integration.android.IntentIntegrator

class TelaQRCode : AppCompatActivity() {

    private val obterResultado = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {

        val produtos = HashMap <String, String>()
        produtos ["7894900010015"] = "REFRIGERANTE COCA-COLA LATA 350ML|7894900010015|A|1|1"
        produtos ["7894900011517"] = "REFRIGERANTE COCA-COLA GARRAFA 2L|7894900011517|A|1|2"
        produtos ["7891991000833"] = "REFRIGERANTE SODA LIMONADA ANTARTIC LATA 350ML|7891991000833|A|1|3"
        produtos ["7891991011020"] = "REFRIGERANTE GUARANA ANTARCTICA LATA 350ML|7891991011020|A|2|1"
        produtos ["7898712836870"] = "REFRIGERANTE GUARANA ANTARCTICA 2L|7898712836870|A|2|2"
        produtos ["7894900039924"] = "REFRIGERANTE FANTA LARANJA 2L|7894900039924|A|2|3"
        produtos ["7894900031201"] = "REFRIGERANTE FANTA LARANJA LATA 350ML|7894900031201|A|2|4"
        produtos ["7892840800079"] = "REFRIGERANTE PEPSI LATA 350ML|7892840800079|A|3|1"
        produtos ["7892840813017"] = "REFRIGERANTE PEPSI 2L|7892840813017|A|3|2"
        produtos ["7896004000855"] = "SUCRILHOS KELLOGG'S ORIGINAL 250G|7896004000855|B|1|1"
        produtos ["7896004003979"] = "SUCRILHOS KELLOGG'S CHOCOLATE 320G|7896004003979|B|1|2"
        produtos ["7896110005140"] = "PAPEL HIGIÊNICO PERSONAL FOLHA SIMPLES NEUTRO 60 METROS 4 UNIDADES|7896110005140|B|2|1"
        produtos ["7896104998953"] = "PAPEL HIGIÊNICO MILI 4R|7896104998953|B|2|2"
        produtos ["7896076002146"] = "PAPEL HIGIENICO DAMA 60MTR|7896076002146|B|2|3"
        produtos ["7896276060021"] = "ARROZ AGULHINHA ARROZAL T1 5KG|7896276060021|C|1|1"
        produtos ["7898295150189"] = "ARROZ SABOROSO 5KG|7898295150189|C|1|2"
        produtos ["7896086423030"] = "ARROZ TRIMAIS 5KG|7896086423030|C|1|3"
        produtos ["7896864400192"] = "FEIJAO PICININ 1KG|7896864400192|C|2|1"
        produtos ["7897924800877"] = "FEIJAO PRETO VENEZA 1KG|7897924800877|C|2|2"
        produtos ["7898084090030"] = "FEIJÃO PEREIRA CARIOQUINHA 1KG|7898084090030|C|2|3"
        produtos ["7891959004415"] = "AÇUCAR REFINADO DOÇULA 1KG|7891959004415|D|1|1"
        produtos ["7896032501010"] = "AÇÚCAR REFINADO DA BARRA 1KG|7896032501010|D|1|2"
        produtos ["7896109801005"] = "AÇÚCAR REFINADO ESPECIAL GUARANI 1KG|7896109801005|D|1|3"
        produtos ["7896319420546"] = "ACUCAR REFINADO CLARION 1KG|7896319420546|D|2|1"
        produtos ["7896089028935"] = "CAFÉ TORRADO MOÍDO POUCHE CAFÉ DO PONTO 500G|7896089028935|D|2|2"
        produtos ["7898286200077"] = "CAFE MARATA 500G|7898286200077|D|2|3"
        produtos ["7891910010905"] = "CAFE CABOCLO 500G|7891910010905|D|3|1"
        produtos ["7898079250012"] = "CAFE FIORENZA 500G|7898079250012|D|3|2"
        produtos ["7891107000504"] = "OLEO DE SOJA SOYA 1L|7891107000504|E|1|1"
        produtos ["7896334200550"] = "OLEO DE SOJA GRANOL 1L|7896334200550|E|2|1"
        produtos ["7896036090107"] = "OLEO DE SOJA VELEIRO 1L|7896036090107|E|3|1"

        val intentResult = IntentIntegrator.parseActivityResult(it.resultCode, it.data)
        val listaProdutos = intentResult.contents.toString().split("|")
        val teste = Produto();

        for (prod in listaProdutos) {

            val listaProdutosSplit = prod.split(":")
            val listaProdutosInd = listaProdutosSplit[0]
            val produtoFinal = produtos[listaProdutosInd]?.split("|")

            teste.descricao = produtoFinal?.get(0)
            teste.codigo = produtoFinal?.get(1)
            teste.rua = produtoFinal?.get(2)
            teste.numero = produtoFinal?.get(3)
            teste.andar = produtoFinal?.get(4)

            Toast.makeText(this, "Teste"+produtoFinal?.get(0), Toast.LENGTH_LONG).show()
        }

        Toast.makeText(this, "TesteDescr"+teste.descricao, Toast.LENGTH_LONG).show()

        if (intentResult.contents != null) {
//            Toast.makeText(this, resultPipe[1].toString(), Toast.LENGTH_LONG).show()
//            if (intentResult.contents.toString().compareTo(produtos?.split("|")) != 0) {
//
//            }
        }



    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_qrcode)

        val texto = "Aperte na camera para scanear os produtos";

        val btnLerQrCode: Button = findViewById(R.id.btnAbrirQR)
        btnLerQrCode.setOnClickListener {
            val integrator: IntentIntegrator = IntentIntegrator(this)
            integrator.setPrompt("Leitura de QRCode")
            obterResultado.launch(integrator.createScanIntent())
        }

    }

}
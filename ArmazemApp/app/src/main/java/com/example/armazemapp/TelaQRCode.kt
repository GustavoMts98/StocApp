package com.example.armazemapp

import com.example.armazemapp.Produto
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.core.view.setPadding
import com.google.zxing.integration.android.IntentIntegrator
import com.journeyapps.barcodescanner.CaptureActivity
import org.w3c.dom.Text
import java.util.ArrayList

class TelaQRCode : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_qrcode)


        val btnLerQrCode: Button = findViewById(R.id.btnAbrirQR)
        btnLerQrCode.setOnClickListener {
            val integrator: IntentIntegrator = IntentIntegrator(this)
            integrator.setPrompt("Leitura de QRCode")
            obterResultado.launch(integrator.createScanIntent())
        }


    }

    val produtosArray : ArrayList<String> = arrayListOf();
    public val obterResultado = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        var iniciarSeparacaoInd = false;
        var isSeparacao = false;
        if(isSeparacao== false) {
            val editTextCliqueBtn : TextView = findViewById(R.id.editTextCliqueBtn)
            val btnScanear : Button = findViewById(R.id.btnAbrirQR)

            editTextCliqueBtn.setVisibility(View.VISIBLE);
            btnScanear.setVisibility(View.VISIBLE);

        }
        val produtos = HashMap <String, String>()
        produtos ["7894900010015"] = "REFRIGERANTE COCA-COLA LATA 350ML|7894900010015|A|1|1|1000|true"
        produtos ["7894900011517"] = "REFRIGERANTE COCA-COLA GARRAFA 2L|7894900011517|A|1|2|1000|false"
        produtos ["7891991000833"] = "REFRIGERANTE SODA LIMONADA ANTARTIC LATA 350ML|7891991000833|A|1|3|1000|false"
        produtos ["7891991011020"] = "REFRIGERANTE GUARANA ANTARCTICA LATA 350ML|7891991011020|A|2|1|1000|false"
        produtos ["7898712836870"] = "REFRIGERANTE GUARANA ANTARCTICA 2L|7898712836870|A|2|2|1000|false"
        produtos ["7894900039924"] = "REFRIGERANTE FANTA LARANJA 2L|7894900039924|A|2|3|1000|false"
        produtos ["7894900031201"] = "REFRIGERANTE FANTA LARANJA LATA 350ML|7894900031201|A|2|4|1000|false"
        produtos ["7892840800079"] = "REFRIGERANTE PEPSI LATA 350ML|7892840800079|A|3|1|1000|false"
        produtos ["7892840813017"] = "REFRIGERANTE PEPSI 2L|7892840813017|A|3|2|1000|false"
        produtos ["7896004000855"] = "SUCRILHOS KELLOGG'S ORIGINAL 250G|7896004000855|B|1|1|1000|false"
        produtos ["7896004003979"] = "SUCRILHOS KELLOGG'S CHOCOLATE 320G|7896004003979|B|1|2|1000|false"
        produtos ["7896110005140"] = "PAPEL HIGI??NICO PERSONAL FOLHA SIMPLES NEUTRO 60 METROS 4 UNIDADES|7896110005140|B|2|1|1000|false"
        produtos ["7896104998953"] = "PAPEL HIGI??NICO MILI 4R|7896104998953|B|2|2|1000|false"
        produtos ["7896076002146"] = "PAPEL HIGIENICO DAMA 60MTR|7896076002146|B|2|3|1000|false"
        produtos ["7896276060021"] = "ARROZ AGULHINHA ARROZAL T1 5KG|7896276060021|C|1|1|1000|false"
        produtos ["7898295150189"] = "ARROZ SABOROSO 5KG|7898295150189|C|1|2|1000|false"
        produtos ["7896086423030"] = "ARROZ TRIMAIS 5KG|7896086423030|C|1|3|1000|false"
        produtos ["7896864400192"] = "FEIJAO PICININ 1KG|7896864400192|C|2|1|1000|false"
        produtos ["7897924800877"] = "FEIJAO PRETO VENEZA 1KG|7897924800877|C|2|2|1000|false"
        produtos ["7898084090030"] = "FEIJ??O PEREIRA CARIOQUINHA 1KG|7898084090030|C|2|3|1000|false"
        produtos ["7891959004415"] = "A??UCAR REFINADO DO??ULA 1KG|7891959004415|D|1|1|1000|false"
        produtos ["7896032501010"] = "A????CAR REFINADO DA BARRA 1KG|7896032501010|D|1|2|1000|false"
        produtos ["7896109801005"] = "A????CAR REFINADO ESPECIAL GUARANI 1KG|7896109801005|D|1|3|1000|false"
        produtos ["7896319420546"] = "ACUCAR REFINADO CLARION 1KG|7896319420546|D|2|1|1000|false"
        produtos ["7896089028935"] = "CAF?? TORRADO MO??DO POUCHE CAF?? DO PONTO 500G|7896089028935|D|2|2|1000|false"
        produtos ["7898286200077"] = "CAFE MARATA 500G|7898286200077|D|2|3|1000|false"
        produtos ["7891910010905"] = "CAFE CABOCLO 500G|7891910010905|D|3|1|1000|false"
        produtos ["7898079250012"] = "CAFE FIORENZA 500G|7898079250012|D|3|2|1000|false"
        produtos ["7891107000504"] = "OLEO DE SOJA SOYA 1L|7891107000504|E|1|1|1000|false"
        produtos ["7896334200550"] = "OLEO DE SOJA GRANOL 1L|7896334200550|E|2|1|1000|false"
        produtos ["7896036090107"] = "OLEO DE SOJA VELEIRO 1L|7896036090107|E|3|1|1000|false"

        val intentResult = IntentIntegrator.parseActivityResult(it.resultCode, it.data)
        val listaProdutos = intentResult.contents.toString().split("|")

        val produtosASeparar : MutableList<Produto> = mutableListOf();

        for (prod in listaProdutos) {

            val produtosCodEQtd = prod.split(":")
            val produtoFinal = produtos[produtosCodEQtd[0]]?.split("|")
            val produto = Produto();
            val quantidadeEstoque = produtoFinal?.get(5)

            produto.descricao = produtoFinal?.get(0)
            produto.codigo = produtoFinal?.get(1)
            produto.rua = produtoFinal?.get(2)
            produto.numero = produtoFinal?.get(3)
            produto.andar = produtoFinal?.get(4)
            produto.quantidade = produtosCodEQtd[1].toInt();
            produto.saldoEstoque = quantidadeEstoque!!?.toInt()
            produto.separacao = produtoFinal?.get(6)

            produtosASeparar.add(produto);
        }

        isSeparacao = true;

        if(isSeparacao == true) {
            val editTextCliqueBtn : TextView = findViewById(R.id.editTextCliqueBtn)
            val btnScanear : Button = findViewById(R.id.btnAbrirQR)
            val btnIniciar : Button = findViewById(R.id.iniciarSeparacaoBtn)
            val txtLista : TextView = findViewById(R.id.listaProdutosTxt)

            editTextCliqueBtn.setVisibility(View.GONE);
            btnScanear.setVisibility(View.GONE);
            btnIniciar.setVisibility(View.VISIBLE);
            txtLista.setVisibility(View.VISIBLE);

        }
        val linearLayout : LinearLayout = findViewById(R.id.llayout01);
        for (prod in produtosASeparar) {

            val listaDeProdutosTxt : TextView = TextView(this);
            listaDeProdutosTxt.setText(prod.descricao)
            listaDeProdutosTxt.setPadding(2,2,2,2)
            linearLayout.addView(listaDeProdutosTxt)


        }


        val btnIniciarSeparacao: Button = findViewById(R.id.iniciarSeparacaoBtn);
        btnIniciarSeparacao.setOnClickListener {
            val linearLayout01 : LinearLayout = findViewById(R.id.llayout01);
            val txtLista : TextView = findViewById(R.id.listaProdutosTxt)

            linearLayout01.setVisibility(View.GONE);
            btnIniciarSeparacao.setVisibility(View.GONE)
            txtLista.setVisibility(View.GONE)
            iniciarSeparacaoInd = true;

            val linearLayout02 : LinearLayout = findViewById(R.id.llayout02);


            for (prod in produtosASeparar) {
                if (prod.quantidade > prod.saldoEstoque) {
                    Toast.makeText(this, "Produto: "+prod.descricao+ " - Saldo insuficiente.", Toast.LENGTH_LONG).show()
                }
                else {
                    val btnEncerrarSerapacao: Button = findViewById(R.id.btnEncerrarSerapacao)
                    val chkSeparacao : CheckBox = CheckBox(this);
                    val btnLerQRCodeProduto : Button = Button(this);
                    val descricaoProd : TextView = TextView(this);
                    val ruaNumeroAndar : TextView = TextView(this);
                    val quantidadeProd : TextView = TextView(this);
                    val quantidadeEstoque = prod.saldoEstoque
                    val txtRuaNumeroAndar = "Rua " + prod.rua + " - " + "Numero " + prod.numero + " - " + "Andar " + prod.andar
                    val txtQuantidade = prod.quantidade.toString() + " unidades" + " / Estoque: "+quantidadeEstoque

                    val produtoArrayToSplit = prod.descricao+"|"+prod.codigo+"|"+prod.rua+"|"+prod.numero+"|"+prod.andar+"|"+
                            prod.quantidade.toString()+"|"+prod.saldoEstoque.toString()

//                    val produtosArray : ArrayList<String> = arrayListOf();
                    produtosArray.add(produtoArrayToSplit)


                    descricaoProd.setText(prod.descricao)
                    descricaoProd.setPadding(0,2,0,0)
                    ruaNumeroAndar.setText(txtRuaNumeroAndar)
                    quantidadeProd.setText(txtQuantidade)
                    btnEncerrarSerapacao.setVisibility(View.VISIBLE)
                    chkSeparacao.setVisibility(View.GONE)
                    linearLayout02.setVisibility(View.VISIBLE)
                    linearLayout02.addView(descricaoProd)
                    linearLayout02.addView(ruaNumeroAndar)
                    linearLayout02.addView(quantidadeProd)
                    linearLayout02.addView(chkSeparacao)

                    btnLerQRCodeProduto.setText("Ler QR")
                    btnLerQRCodeProduto.setTextColor(resources.getColor(R.color.white))
                    btnLerQRCodeProduto.setBackgroundColor(resources.getColor(R.color.orangePrimary))
                    linearLayout02.addView(btnLerQRCodeProduto)

                    btnLerQRCodeProduto.setOnClickListener {
                        val parametros = Bundle()
                        parametros.putString("produto",produtoArrayToSplit)
                        val telaSeparacao = Intent(this, TelaSeparacao::class.java)
                        telaSeparacao.putExtras(parametros)
                        startActivity(telaSeparacao)

                    }

                    btnEncerrarSerapacao.setOnClickListener {
                        val telaInicial = Intent(this, TelaQRCode::class.java)
                        startActivity(telaInicial)
                    }
                }


            }
        }



    }


    private fun Separar() {
        for (prod in produtosArray) {
            val produtoASeparar = prod.split("|")
        }
    }

    private fun AbrirTelaSeparacao() {
        val irParaSeparacao = Intent(this, TelaSeparacao::class.java)
        startActivity(irParaSeparacao)
    }


}


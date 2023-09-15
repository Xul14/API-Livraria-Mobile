package br.senai.sp.jandira.ds3t_api_livraria

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import com.google.gson.JsonObject
import kotlinx.coroutines.launch

class Cadastro_Categoria : AppCompatActivity() {

    //Criação do atributo que vai represrntar a ApiService
    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_categoria)

        //Criação da instancia aticva do retrofit
        apiService = RetrofitHelper.getInstance().create(ApiService::class.java)

        //Recupera o objeto de eidtText do formulàrio de cadastro de categoria
        var txtCategoria = findViewById<EditText>(R.id.txtCategoria)

        //Trata o evento de click do botào de cadastrar
        findViewById<Button>(R.id.btnCadastrarCategoria).setOnClickListener {

            //Recupera o valor digitado pelo usuario
            val nomeCategoria = txtCategoria.text

            //Envia o dados de categoria para cadastro na api
            createCategory(nomeCategoria.toString())
        }
    }

    private fun createCategory(nome_categoria: String) {

        lifecycleScope.launch {

            //Criação do corpo de dados em foemato JSON
            val body = JsonObject().apply {
                addProperty("nome_categoria", nome_categoria)
            }

            //Chamada e envio de dados para a rota de cadastrar categoria
            val result = apiService.createCategory(body)

            if(result.isSuccessful){
                val msg = result.body()?.get("mensagemStatus")
                Log.i("Create Category", "Status: ${msg}")
            }else{
                Log.i("Create Category Error", "Status: ${result.message()}")
            }
        }
    }
}
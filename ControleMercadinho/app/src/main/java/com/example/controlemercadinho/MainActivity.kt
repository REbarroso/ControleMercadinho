package com.example.controlemercadinho

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import com.example.controlemercadinho.data.DataSource
import com.example.controlemercadinho.data.ProductRepository
import com.example.controlemercadinho.ui.theme.ControleMercadinhoTheme

class MainActivity : ComponentActivity() {
    // Instanciando a camada de dados
    private val productRepository = ProductRepository(DataSource())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ControleMercadinhoTheme {
                ProductNameInput(productRepository) // Passando o repositório para a UI
            }
        }
    }
}

@Composable
fun ProductNameInput(productRepository: ProductRepository) {
    // Estado para o nome do produto
    var productName by remember { mutableStateOf("") }

    // Estado para a lista de produtos
    var productList by remember { mutableStateOf(listOf<String>()) }

    // Acesso ao contexto dentro da função composable
    val context = LocalContext.current

    // Usando Column para organizar os itens na tela
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        // Campo de texto para o nome do produto
        TextField(
            value = productName,
            onValueChange = { productName = it },
            label = { Text("Nome do Produto") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botão para salvar o produto
        Button(
            onClick = {
                // Verifica se o campo não está vazio
                if (productName.isNotEmpty()) {
                    // Adiciona o nome do produto no repositório
                    productRepository.addProductToRepository(productName)
                    // Limpa o campo de texto
                    productName = ""
                    // Exibe o Toast
                    Toast.makeText(context, "Produto '$productName' salvo!", Toast.LENGTH_SHORT).show()

                    // Atualiza a lista de produtos
                    productList = productRepository.getProductList()
                } else {
                    // Exibe o Toast para alertar que o campo está vazio
                    Toast.makeText(context, "Por favor, insira o nome do produto.", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Salvar Produto")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Exibindo a lista de produtos salvos
        if (productList.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(productList.size) { index ->
                    Text(
                        text = productList[index],
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProductNameInput() {
    val productRepository = ProductRepository(DataSource())
    ControleMercadinhoTheme {
        ProductNameInput(productRepository) // Passando o repositório para a UI
    }
}

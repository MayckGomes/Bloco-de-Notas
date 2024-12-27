package mayckgomes.com.noteblocks.telas

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import mayckgomes.com.noteblocks.criarNotas
import mayckgomes.com.noteblocks.editarNota
import mayckgomes.com.noteblocks.funçôes.Nota
import mayckgomes.com.noteblocks.funçôes.carregarNotas

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavController){

    val listaNotas:List<Nota> = carregarNotas()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("Bloco de notas", color = MaterialTheme.colorScheme.primary)})
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {navController.navigate(criarNotas)}){
                Icon(imageVector = Icons.Filled.Add, contentDescription = null)
            }
        },
        content = { padding ->

            if (listaNotas.isEmpty()){
                Column(modifier = Modifier
                    .padding(padding)
                    .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally)
                {

                    Text("Ainda não há notas criadas!!")

                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                ) {

                    items(listaNotas) { item ->
                        ListItem(
                            modifier = Modifier.clickable {
                                navController.navigate(editarNota(nota = listOf(
                                    item.id.toString(),
                                    item.titulo,
                                    item.texto)))
                            },
                            headlineContent = {
                                Text(item.titulo,
                                color = MaterialTheme.colorScheme.tertiary) },

                            overlineContent = {
                                Text(item.data,
                                    color = MaterialTheme.colorScheme.tertiary) }
                        )
                        HorizontalDivider()
                    }
                }
            }

        })
}
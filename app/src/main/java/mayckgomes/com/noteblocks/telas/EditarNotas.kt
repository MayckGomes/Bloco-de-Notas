package mayckgomes.com.noteblocks.telas

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import mayckgomes.com.noteblocks.funçôes.Nota
import mayckgomes.com.noteblocks.funçôes.dataEHora
import mayckgomes.com.noteblocks.funçôes.editarNota
import mayckgomes.com.noteblocks.funçôes.excluirNota
import mayckgomes.com.noteblocks.home
import mayckgomes.com.noteblocks.ui.theme.Azul
import mayckgomes.com.noteblocks.ui.theme.Branco

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditarNotas(navController: NavController,nota:List<String>){

    var titulo by rememberSaveable() {
        mutableStateOf(nota[1])
    }

    var texto by rememberSaveable() {
        mutableStateOf(nota[2])
    }

    var isClickedExcluir by rememberSaveable {
        mutableStateOf(false)
    }

    var isClickedEditar by rememberSaveable {
        mutableStateOf(false)
    }

    Scaffold(
        modifier = Modifier.padding(bottom = 45.dp),
        topBar = {
            TopAppBar(
                modifier = Modifier.padding(start = 10.dp),
                title = { Text("Editar Nota",
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(start = 10.dp))},

                navigationIcon = {
                    Icon(
                        modifier = Modifier.clickable { navController.navigate(home) },
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null) }) },

        bottomBar = {
            Row(Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround) {

                Button(onClick = {isClickedExcluir = true})
                {
                    Text("EXCLUIR")
                }

                if (isClickedExcluir){
                    isClickedExcluir = false
                    excluirNota(id = nota[0].toInt())
                    navController.navigate(home)
                }


                OutlinedButton(onClick = { isClickedEditar = true }) {
                    Text("SALVAR")
                }

                if (isClickedEditar == true){
                    isClickedEditar = false
                    editarNota(Nota(
                        id = nota[0].toInt(),
                        titulo = titulo,
                        texto = texto,
                        data = dataEHora()
                    ))
                    navController.navigate(home)
                }

            }
        },
        content = {paddingValues ->
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)){

                //Titulo
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = titulo,
                    textStyle = TextStyle(color = MaterialTheme.colorScheme.tertiary, fontSize = 23.sp),
                    onValueChange = {titulo = it},
                    label = { Text("Titulo") },
                    singleLine = true,
                    colors = TextFieldDefaults.colors(focusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        cursorColor = MaterialTheme.colorScheme.primary,
                        focusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )

                //Texto
                TextField(
                    modifier = Modifier.fillMaxSize(1f),
                    value = texto,
                    onValueChange = {texto = it},
                    label = { Text("Texto") },
                    textStyle = TextStyle(color = MaterialTheme.colorScheme.tertiary),
                    colors = TextFieldDefaults.colors(focusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        cursorColor = MaterialTheme.colorScheme.primary,
                        focusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
            }
        }
    )


}



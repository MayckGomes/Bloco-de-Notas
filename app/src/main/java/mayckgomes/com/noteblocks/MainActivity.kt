package mayckgomes.com.noteblocks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import mayckgomes.com.noteblocks.telas.CriarNotas
import mayckgomes.com.noteblocks.telas.EditarNotas
import mayckgomes.com.noteblocks.telas.Home
import mayckgomes.com.noteblocks.ui.theme.NoteBlocksTheme

@Serializable
object home

@Serializable
object criarNotas

@Serializable
data class editarNota(
    val nota:List<String>
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NoteBlocksTheme{
                Navegacao()
            }
        }
    }
}

@Composable
fun Navegacao(){
    val navController = rememberNavController()

    NavHost(navController, startDestination = home , builder = {
        composable<home>{ Home(navController) }

        composable<criarNotas>{ CriarNotas(navController) }

        composable<editarNota> {
            val args = it.toRoute<editarNota>()
            EditarNotas(navController,args.nota) }
    }
    )

}
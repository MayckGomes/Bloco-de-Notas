package mayckgomes.com.noteblocks.funçôes

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import kotlinx.serialization.Serializable
import kotlin.math.log

@Serializable
data class Nota(
    val id:Int,
    var titulo:String,
    var texto:String,
    var data:String
)


@Composable
fun pegarEditor(): SharedPreferences {
    val baseDados = LocalContext.current.getSharedPreferences("Notas",Context.MODE_PRIVATE)
    return baseDados
}

@Composable
fun carregarNotas():List<Nota>{
    val editor = pegarEditor()
    val lista:MutableList<Nota> = mutableListOf()

    val todasNotas = editor.all

    for (notas in todasNotas){
        val nota = notas.value.toString().split(",")

        lista.add(Nota(id = nota[0].toInt(),
            titulo = nota[1],
            texto = nota[2],
            data = nota[3]))
    }
    return lista.toList()
}

@Composable
fun addNotas(nota:Nota){
    val editor = pegarEditor()
    editor.edit().putString("${nota.id}","${nota.id},${nota.titulo},${nota.texto},${nota.data}")
        .apply()
}

@Composable
fun editarNota(notas:Nota){
    val editor = pegarEditor()
    editor.edit().putString("${notas.id}","${notas.id},${notas.titulo},${notas.texto},${notas.data}")
        .apply()
}

@Composable
fun excluirNota(id:Int){
    val idExcluir = id
    val editor = pegarEditor()
    val notas = editor.all
    var idAtual = 0

    editor.edit().clear().apply()

    for (nota in notas){
        val listaItems = nota.value.toString().split(",")

        val id = listaItems[0]
        val titulo = listaItems[1]
        val texto = listaItems[2]
        val data = listaItems[3]

        if (id.toInt() == idExcluir){
            continue

        } else if (id.toInt() == idAtual){
            editor.edit().putString(id,"$id,$titulo,$texto,$data").apply()

        } else {
            editor.edit().putString("$idAtual","$idAtual,$titulo,$texto,$data").apply()
        }

        idAtual++

    }

}
@Composable
fun lenListNotas():Int{
    val editor = pegarEditor()
    val todasNotas = editor.all

    return todasNotas.size
}
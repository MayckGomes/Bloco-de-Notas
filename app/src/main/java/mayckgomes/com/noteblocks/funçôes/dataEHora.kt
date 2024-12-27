package mayckgomes.com.noteblocks.funçôes

import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

fun dataEHora():String{
    return "${LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/uuuu"))} as ${
        LocalTime.now().format(
            DateTimeFormatter.ofPattern("HH:mm"))}"
}
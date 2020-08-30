import com.trendyol.model.TyDeepLink
import com.trendyol.replaceTurkishChars
import org.springframework.web.util.UriComponentsBuilder
import java.text.Normalizer


class WebUrl

fun String.asDeepLink(): TyDeepLink {

    val a = TyDeepLink(this)

    return a
}

fun clearTurkishChars(str: String): String {
    var ret = str
    val turkishChars = charArrayOf(0x131.toChar(), 0x130.toChar(), 0xFC.toChar(), 0xDC.toChar(), 0xF6.toChar(), 0xD6.toChar(), 0x15F.toChar(), 0x15E.toChar(), 0xE7.toChar(), 0xC7.toChar(), 0x11F.toChar(), 0x11E.toChar())
    val englishChars = charArrayOf('i', 'I', 'u', 'U', 'o', 'O', 's', 'S', 'c', 'C', 'g', 'G')
    for (i in turkishChars.indices) {
        ret = ret.replace(turkishChars[i], englishChars[i])
    }
    return ret
}

fun main(){

    println("İŞKIıdöçğĞıI".replaceTurkishChars().toLowerCase())

}
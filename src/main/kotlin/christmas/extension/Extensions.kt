package christmas.extension

fun String.toStringIntMap(): MutableMap<String, Int> {
    val bundle = this.split(",")
    val result =  mutableMapOf<String, Int>()
    bundle.forEach { element ->
        val elementBundle = element.split("-")
        result[elementBundle.first()] = elementBundle.last().toInt()
    }
    return result
}

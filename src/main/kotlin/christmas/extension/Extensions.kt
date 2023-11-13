package christmas.extension

import christmas.model.menu.Menu

fun String.toStringList(): List<String> {
    return this.split(",")
}

fun String.toStringToMenuList(): List<Menu> {
    val orders = this.split(",")
    return orders.map { order ->
        val bundles = order.split("-")
        Menu(bundles.first(), bundles.last().toInt())
    }
}

fun List<String>.toCountList(): List<String> {
    return this.map { count ->
        count.split("-")[1]
    }
}

fun List<String>.toMenuNameList(): List<String> {
    return this.map { menu ->
        menu.split("-")[0]
    }
}
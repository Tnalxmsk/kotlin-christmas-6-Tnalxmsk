package christmas.extension

import christmas.model.menu.Menu

fun String.toStringToMenuList(): List<Menu> {
    val orders = this.split(",")
    return orders.map{ order ->
        val bundles = order.split("-")
        Menu(bundles.first(), bundles.last().toInt())
    }
}

package christmas.model

import christmas.model.menu.Dessert

class Order(private val orderMenus: Map<String, Int>) {
    private fun isDessert(orderMenu: String): Boolean{
        return Dessert.entries.any { it.menuName.contains(orderMenu) }
    }

    fun findDessert(): Map<String, Int> = orderMenus.filter { isDessert(it.key) }

    fun getOrderMenus(): Map<String, Int> = orderMenus
}
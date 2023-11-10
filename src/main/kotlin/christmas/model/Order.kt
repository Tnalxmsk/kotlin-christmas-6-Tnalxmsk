package christmas.model

import christmas.model.menu.Dessert
import christmas.model.menu.MainDish

class Order(private val orderMenus: Map<String, Int>) {
    private fun isDessert(orderMenu: String): Boolean = Dessert.entries.any { it.menuName.contains(orderMenu) }

    private fun isMainDish(orderMenu: String): Boolean = MainDish.entries.any { it.menuName.contains(orderMenu) }

    fun findDessert(): Map<String, Int> = orderMenus.filter { menu -> isDessert(menu.key) }

    fun findMainDish(): Map<String, Int> = orderMenus.filter { menu -> isMainDish(menu.key) }

    fun getOrderMenus(): Map<String, Int> = orderMenus
}
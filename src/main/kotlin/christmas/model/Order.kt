package christmas.model

import christmas.model.menu.Dessert
import christmas.model.menu.MainDish
import christmas.model.menu.Menu

class Order(private val orderMenus: List<Menu>) {
    private fun isDessert(orderMenu: String): Boolean = Dessert.entries.any { it.menuName.contains(orderMenu) }

    private fun isMainDish(orderMenu: String): Boolean = MainDish.entries.any { it.menuName.contains(orderMenu) }

    fun findDessert(): List<Menu> = orderMenus.filter { menu -> isDessert(menu.menuName) }

    fun findMainDish(): List<Menu> = orderMenus.filter { menu -> isMainDish(menu.menuName) }

    fun getOrderMenus(): List<Menu> = orderMenus
}
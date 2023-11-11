package christmas.model

import christmas.model.menu.*

class Order(private val orderMenus: List<Menu>) {
    private fun isDessert(orderMenu: String): Boolean = Dessert.entries.any { it.menuName.contains(orderMenu) }

    private fun isMainDish(orderMenu: String): Boolean = MainDish.entries.any { it.menuName.contains(orderMenu) }

    private fun isAppetizer(orderMenu: String): Boolean = Appetizer.entries.any { it.menuName.contains(orderMenu) }

    private fun isBeverage(orderMenu: String): Boolean = Beverage.entries.any { it.menuName.contains(orderMenu) }

    fun findDessert(): List<Menu> = orderMenus.filter { menu -> isDessert(menu.menuName) }

    fun findMainDish(): List<Menu> = orderMenus.filter { menu -> isMainDish(menu.menuName) }

    fun findAppetizer(): List<Menu> = orderMenus.filter { menu -> isAppetizer(menu.menuName) }

    fun findBeverage(): List<Menu> = orderMenus.filter { menu -> isBeverage(menu.menuName) }

    fun getOrderMenus(): List<Menu> = orderMenus
}
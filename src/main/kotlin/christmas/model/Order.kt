package christmas.model

import christmas.model.menu.*

class Order(private val orderMenus: List<Menu>) {
    fun findDessert(): List<Menu> = orderMenus.filter { menu -> MenuChecker.isDessert(menu.menuName) }

    fun findMainDish(): List<Menu> = orderMenus.filter { menu -> MenuChecker.isMainDish(menu.menuName) }

    fun findAppetizer(): List<Menu> = orderMenus.filter { menu -> MenuChecker.isAppetizer(menu.menuName) }

    fun findBeverage(): List<Menu> = orderMenus.filter { menu -> MenuChecker.isBeverage(menu.menuName) }

    fun getOrderMenus(): List<Menu> = orderMenus
}
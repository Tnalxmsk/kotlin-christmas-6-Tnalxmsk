package christmas.model

import christmas.model.menu.Appetizer
import christmas.model.menu.Beverage
import christmas.model.menu.Dessert
import christmas.model.menu.MainDish

class Price(private val order: Order) {
    private var totalPrice = 0

    init {
        addPrice()
    }

    private fun addPrice() {
        order.findDessert().forEach {
            totalPrice += (Dessert.findByMenuName(it.menuName)?.price ?: 0) * it.count
        }
        order.findMainDish().forEach {
            totalPrice += (MainDish.findByMenuName(it.menuName)?.price ?: 0) * it.count
        }
        order.findAppetizer().forEach {
            totalPrice += (Appetizer.findByMenuName(it.menuName)?.price ?: 0) * it.count
        }
        order.findBeverage().forEach {
            totalPrice += (Beverage.findByMenuName(it.menuName)?.price ?: 0) * it.count
        }
    }

    fun getTotalPrice(): Int = totalPrice
}
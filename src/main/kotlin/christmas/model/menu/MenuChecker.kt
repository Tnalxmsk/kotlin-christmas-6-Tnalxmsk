package christmas.model.menu

object MenuChecker {
    fun isDessert(orderMenu: String): Boolean = Dessert.entries.any { it.menuName.contains(orderMenu) }

    fun isMainDish(orderMenu: String): Boolean = MainDish.entries.any { it.menuName.contains(orderMenu) }

    fun isAppetizer(orderMenu: String): Boolean = Appetizer.entries.any { it.menuName.contains(orderMenu) }

    fun isBeverage(orderMenu: String): Boolean = Beverage.entries.any { it.menuName.contains(orderMenu) }
}
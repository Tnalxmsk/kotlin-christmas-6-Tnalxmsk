package christmas.model.menu

enum class Beverage(val menuName: String, val price: Int) {
    ZERO_COLA("제로콜라", 3000),
    RED_WINE("레드와인", 60000),
    CHAMPAGNE("샴페인", 25000);

    companion object {
        fun findByMenuName(menuName: String): Beverage? = Beverage.entries.find { it.menuName == menuName }
    }
}
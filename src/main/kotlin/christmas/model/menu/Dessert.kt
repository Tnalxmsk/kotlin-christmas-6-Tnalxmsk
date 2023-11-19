package christmas.model.menu

enum class Dessert(val menuName: String, val price: Int) {
    CHOCO_CAKE("초코케이크", 15000),
    ICE_CREAM("아이스크림", 5000);

    companion object {
        fun findByMenuName(menuName: String): Dessert? = Dessert.entries.find { it.menuName == menuName }
    }
}
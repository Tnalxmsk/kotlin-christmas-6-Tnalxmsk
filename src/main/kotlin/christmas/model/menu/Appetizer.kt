package christmas.model.menu

enum class Appetizer(val menuName: String, val price: Int) {
    SOUP("양송이수프", 6_000),
    TAPAS("타파스", 5_500),
    SALAD("시저샐러드", 8000);

    companion object {
        fun findByMenuName(menuName: String): Appetizer? = Appetizer.entries.find { it.menuName == menuName }
    }
}
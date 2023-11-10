package christmas.model.menu

enum class MainDish(val menuName: String, val price: Int) {
    T_BONE_STEAK("티본스테이크", 55000),
    BBQ_RIBS("바비큐립", 54000),
    SEAFOOD_PASTA("해산물파스타", 35000),
    CHRISTMAS_PASTA("크리스마스파스타", 25000);

    companion object {
        fun findByMenuName(menuName: String): MainDish? = MainDish.entries.find { it.menuName == menuName }
    }
}
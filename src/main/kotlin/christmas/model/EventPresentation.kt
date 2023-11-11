package christmas.model

object EventPresentation {
    fun checkEventCondition(beforeDiscountTotalPrice: Int): Boolean {
        return beforeDiscountTotalPrice >= 120_000
    }
}
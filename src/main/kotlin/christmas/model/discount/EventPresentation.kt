package christmas.model.discount

import christmas.model.menu.Beverage

object EventPresentation {
    private const val TERMS_AMOUNT = 120_000

    fun checkEventCondition(beforeDiscountTotalPrice: Int): Boolean {
        return beforeDiscountTotalPrice >= TERMS_AMOUNT
    }

    fun getEventPresentationGoodsPrice(): Int {
        return Beverage.CHAMPAGNE.price
    }
}
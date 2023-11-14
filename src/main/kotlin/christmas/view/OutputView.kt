package christmas.view

import christmas.model.Badge
import christmas.model.discount.DecemberDiscount
import christmas.model.discount.EventPresentation
import christmas.model.Order
import christmas.model.Price
import christmas.model.VisitDate

class OutputView(
    private val discount: DecemberDiscount,
    private val visitDate: VisitDate,
    private val price: Price
) {
    fun printMenu(order: Order) {
        println(OutputViewHeader.MENU_HEADER)
        order.getOrderMenus().forEach { menu ->
            println(ORDER_MENUS.format(menu.menuName, menu.count))
        }
        println()
    }

    fun printBeforeDiscountPrice() {
        println(OutputViewHeader.BEFORE_DISCOUNT_HEADER)
        println(BEFORE_DISCOUNT_WON.format(price.getTotalPrice()))
        println()
    }

    fun printPresentationMenu() {
        println(OutputViewHeader.PRESENTATION_HEADER)
        if (EventPresentation.checkEventCondition(price.getTotalPrice())) {
            println(PRESENT_GOODS)
            return
        }
        println(NO_BENEFIT)
    }

    fun printBenefitContent() {
        println(OutputViewHeader.BENEFIT_HEADER)
        if (price.getTotalPrice() < TERMS_AMOUNT) {
            println(NO_BENEFIT)
            return
        }

        printDiscount(D_DAY_DISCOUNT, visitDate.isChristmasDDayEvent(), discount.applyDDayDiscount())
        printDiscount(WEEKDAY_DISCOUNT, visitDate.isWeekday(), discount.applyWeekdayDiscount())
        printDiscount(WEEKEND_DISCOUNT, visitDate.isWeekend(), discount.applyWeekendDiscount())
        printDiscount(SPECIAL_DISCOUNT, visitDate.isSpecialDay(), discount.applySpecialDayDiscount())

        if (EventPresentation.checkEventCondition(price.getTotalPrice()))
            println(PRESENTATION_DISCOUNT.format(discount.applyPresentEvent()))

        println()
    }

    private fun printDiscount(type: String, condition: Boolean, discountedPrice: Int) {
        if (condition) {
            println(DISCOUNT_DETAILS.format(type, discountedPrice))
        }
    }

    fun printBenefitPrice() {
        println(OutputViewHeader.BENEFIT_PRICE_HEADER)
        when (discount.getTotalBenefitAmount()) {
            0 -> println(ZERO_WON)
            else -> println(TOTAL_DISCOUNT_WON.format(discount.getTotalBenefitAmount()))
        }
        println()
    }

    fun printAfterDiscountPrice() {
        println(OutputViewHeader.AFTER_DISCOUNT_HEADER)
        println(AFTER_DISCOUNT_TOTAL_WON.format(price.getAfterDiscountTotalPrice(discount)))
        println()
    }

    fun printEventBadge() {
        println(OutputViewHeader.EVENT_BADGE_HEADER)
        val totalBenefitAmount = discount.getTotalDiscount()
        println(Badge.grantBadge(totalBenefitAmount))
    }

    companion object {
        private const val DISCOUNT_DETAILS = "%s: -%d"
        private const val ORDER_MENUS = "%s %d개"
        private const val NO_BENEFIT = "없음\n"
        private const val ZERO_WON = "0원"
        private const val BEFORE_DISCOUNT_WON = "%d원"
        private const val AFTER_DISCOUNT_TOTAL_WON = "%d원"
        private const val TOTAL_DISCOUNT_WON = "-%d원"
        private const val D_DAY_DISCOUNT = "크리스마스 디데이 할인"
        private const val WEEKDAY_DISCOUNT = "평일 할인"
        private const val WEEKEND_DISCOUNT = "주말 할인"
        private const val SPECIAL_DISCOUNT = "특별 할인"
        private const val PRESENTATION_DISCOUNT = "증정 이벤트: -%d원"
        private const val PRESENT_GOODS = "샴페인 1개\n"
        private const val TERMS_AMOUNT = 10000
    }
}
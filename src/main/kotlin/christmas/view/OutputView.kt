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
    fun printEventInfo() = println(EVENT_INFO.format(visitDate.getVisitDate()))

    fun printMenu(order: Order) {
        println(OutputViewHeader.MENU_HEADER.headerView)
        order.getOrderMenus().forEach { menu ->
            println(ORDER_MENUS.format(menu.menuName, menu.count))
        }
        println()
    }

    fun printBeforeDiscountPrice() {
        println(OutputViewHeader.BEFORE_DISCOUNT_HEADER.headerView)
        println(BEFORE_DISCOUNT_WON.format(price.getTotalPrice()))
        println()
    }

    fun printPresentationMenu() {
        println(OutputViewHeader.PRESENTATION_HEADER.headerView)
        if (EventPresentation.checkEventCondition(price.getTotalPrice())) {
            println(EventView.PRESENT_GOODS.message)
            return
        }
        println(NO_BENEFIT)
    }

    fun printBenefitContent() {
        println(OutputViewHeader.BENEFIT_HEADER.headerView)
        if (price.getTotalPrice() < TERMS_AMOUNT) {
            println(NO_BENEFIT)
            return
        }

        printDiscount(EventView.D_DAY_DISCOUNT.message, visitDate.isChristmasDDayEvent(), discount.applyDDayDiscount())
        printDiscount(EventView.WEEKDAY_DISCOUNT.message, visitDate.isWeekday(), discount.applyWeekdayDiscount())
        printDiscount(EventView.WEEKEND_DISCOUNT.message, visitDate.isWeekend(), discount.applyWeekendDiscount())
        printDiscount(EventView.SPECIAL_DISCOUNT.message, visitDate.isSpecialDay(), discount.applySpecialDayDiscount())

        if (EventPresentation.checkEventCondition(price.getTotalPrice()))
            println(EventView.PRESENTATION_DISCOUNT.message.format(discount.applyPresentEvent()))

        println()
    }

    private fun printDiscount(type: String, condition: Boolean, discountedPrice: Int) {
        if (condition && discountedPrice != 0) {
            println(DISCOUNT_DETAILS.format(type, discountedPrice))
        }
    }

    fun printBenefitPrice() {
        println(OutputViewHeader.BENEFIT_PRICE_HEADER.headerView)
        when (discount.getTotalBenefitAmount()) {
            0 -> println(ZERO_WON)
            else -> println(TOTAL_DISCOUNT_WON.format(discount.getTotalBenefitAmount()))
        }
        println()
    }

    fun printAfterDiscountPrice() {
        println(OutputViewHeader.AFTER_DISCOUNT_HEADER.headerView)
        println(AFTER_DISCOUNT_TOTAL_WON.format(price.getAfterDiscountTotalPrice(discount)))
        println()
    }

    fun printEventBadge() {
        println(OutputViewHeader.EVENT_BADGE_HEADER.headerView)
        val totalBenefitAmount = discount.getTotalBenefitAmount()
        println(Badge.grantBadge(totalBenefitAmount))
    }

    companion object {
        private const val EVENT_INFO = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n"
        private const val DISCOUNT_DETAILS = "%s: -%,d원"
        private const val ORDER_MENUS = "%s %d개"
        private const val NO_BENEFIT = "없음\n"
        private const val ZERO_WON = "0원"
        private const val BEFORE_DISCOUNT_WON = "%,d원"
        private const val AFTER_DISCOUNT_TOTAL_WON = "%,d원"
        private const val TOTAL_DISCOUNT_WON = "-%,d원"
        private const val TERMS_AMOUNT = 10000
    }
}
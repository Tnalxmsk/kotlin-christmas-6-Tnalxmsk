package christmas.view

import christmas.model.*
import christmas.model.date.DateChecker
import christmas.model.discount.DecemberDiscount
import christmas.model.discount.EventPresentation

class OutputView(
    private val discount: DecemberDiscount,
    private val price: Price
) {
    fun printDecemberEventView(date: Int) = println(DECEMBER_EVENT_VIEW.format(date))

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

    fun printBenefitContent(date: Int) {
        println(OutputViewHeader.BENEFIT_HEADER.headerView)
        if (price.getTotalPrice() < TERMS_AMOUNT) {
            println(NO_BENEFIT)
            return
        }
        printDetailAll(date)
        println()
    }

    private fun printDetailAll(date: Int) {
        val check = DateChecker
        printDetail(EventView.D_DAY_DISCOUNT.message, check.isDDayEvent(date), discount.applyDDayDiscount())
        printDetail(EventView.WEEKDAY_DISCOUNT.message, check.isWeekday(date), discount.applyWeekdayDiscount())
        printDetail(EventView.WEEKEND_DISCOUNT.message, check.isWeekend(date), discount.applyWeekendDiscount())
        printDetail(EventView.SPECIAL_DISCOUNT.message, check.isSpecialDay(date), discount.applySpecialDayDiscount())
        printDetail(
            EventView.PRESENTATION_EVENT.message,
            EventPresentation.checkEventCondition(price.getTotalPrice()),
            discount.applyPresentEvent()
        )
    }

    private fun printDetail(type: String, condition: Boolean, discountedPrice: Int) {
        if (condition && (discountedPrice != NONE_DISCOUNT)) {
            println(DISCOUNT_DETAILS.format(type, discountedPrice))
        }
    }

    fun printBenefitPrice() {
        println(OutputViewHeader.BENEFIT_PRICE_HEADER.headerView)
        when (discount.getTotalBenefitAmount()) {
            NONE_DISCOUNT -> println(ZERO_WON)
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
        private const val DECEMBER_EVENT_VIEW = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n"
        private const val DISCOUNT_DETAILS = "%s: -%,d원"
        private const val ORDER_MENUS = "%s %d개"
        private const val NO_BENEFIT = "없음\n"
        private const val ZERO_WON = "0원"
        private const val BEFORE_DISCOUNT_WON = "%,d원"
        private const val AFTER_DISCOUNT_TOTAL_WON = "%,d원"
        private const val TOTAL_DISCOUNT_WON = "-%,d원"
        private const val TERMS_AMOUNT = 10000
        private const val NONE_DISCOUNT = 0
    }
}
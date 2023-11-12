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
        println(MENU_HEADER)
        order.getOrderMenus().forEach { menu ->
            println(ORDER_MENUS.format(menu.menuName, menu.count))
        }
        println()
    }

    // 할인 전 결제 금액을 출력하고
    fun printBeforeDiscountPrice() {
        println(BEFORE_DISCOUNT_HEADER)
        println(BEFORE_DISCOUNT_WON.format(price.getTotalPrice()))
        println()
    }

    // 증정 내용 출력
    fun printPresentationMenu() {
        println(PRESENTATION_HEADER)
        if (EventPresentation.checkEventCondition(price.getTotalPrice())) {
            println(PRESENT_GOODS)
            return
        }
        println(NO_BENEFIT)
    }

    // 혜택 내용 출력
    fun printBenefitContent() {
        println(BENEFIT_HEADER)
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
        println(BENEFIT_PRICE_HEADER)
        when (discount.getTotalDiscount()) {
            0 -> println(ZERO_WON)
            else -> println(TOTAL_DISCOUNT_WON.format(discount.getTotalBenefitAmount()))
        }
        println()
    }

    fun printAfterDiscountPrice() {
        println(AFTER_DISCOUNT_HEADER)
        println(AFTER_DISCOUNT_TOTAL_WON.format(price.getAfterDiscountTotalPrice(discount)))
        println()
    }

    fun printEventBadge() {
        println(EVENT_BADGE_HEADER)
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
        private const val MENU_HEADER = "<주문 메뉴>"
        private const val BEFORE_DISCOUNT_HEADER = "<할인 전 총주문 금액>"
        private const val PRESENTATION_HEADER = "<증정 메뉴>"
        private const val BENEFIT_HEADER = "<혜택 내역>"
        private const val BENEFIT_PRICE_HEADER = "<총혜택 금액>"
        private const val AFTER_DISCOUNT_HEADER = "<할인 후 예상 결제 금액>"
        private const val EVENT_BADGE_HEADER = "<12월 이벤트 배지>"
    }
}
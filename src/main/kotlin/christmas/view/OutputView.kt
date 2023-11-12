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
        println("<주문 메뉴>")
        order.getOrderMenus().forEach { menu ->
            println(ORDER_MENUS.format(menu.menuName, menu.count))
        }
        println()
    }

    // 할인 전 결제 금액을 출력하고
    fun printBeforeDiscountPrice() {
        println("<할인 전 총주문 금액>")
        println(BEFORE_DISCOUNT_WON.format(price.getTotalPrice()))
        println()
    }

    // 증정 내용 출력
    fun printPresentationMenu() {
        println("<증정 메뉴>")
        if (EventPresentation.checkEventCondition(price.getTotalPrice())) {
            println(PRESENT_GOODS)
            return
        }
        println(NO_BENEFIT)
    }

    // 혜택 내용 출력
    fun printBenefitContent() {
        println("<혜택 내역>")
        if (price.getTotalPrice() < 10000) {
            println(NO_BENEFIT)
            return
        }
        if (visitDate.isChristmasDDayEvent())
            println(D_DAY_DISCOUNT.format(discount.applyDDayDiscount()))
        if (visitDate.isWeekday())
            println(WEEKDAY_DISCOUNT.format(discount.applyWeekdayDiscount()))
        if (visitDate.isWeekend())
            println(WEEKEND_DISCOUNT.format(discount.applyWeekendDiscount()))
        if (visitDate.isSpecialDay())
            println(SPECIAL_DISCOUNT.format(discount.applySpecialDayDiscount()))
        if (EventPresentation.checkEventCondition(price.getTotalPrice()))
            println(PRESENTATION_DISCOUNT.format(discount.applyPresentEvent()))
        println()
    }

    // 혜택 받은 금액을 출력
    fun printBenefitPrice() {
        println("<총혜택 금액>")
        when (discount.getTotalDiscount()) {
            0 -> println(ZERO_WON)
            else -> println(TOTAL_DISCOUNT_WON.format(discount.getTotalBenefitAmount()))
        }
        println()
    }

    // 할인 후 결제 금액을 출력
    fun printAfterDiscountPrice() {
        println("<할인 후 예상 결제 금액>")
        println(AFTER_DISCOUNT_TOTAL_WON.format(price.getAfterDiscountTotalPrice(discount)))
        println()
    }

    // 배지를 출력해야겠군
    fun printEventBadge() {
        println("<12월 이벤트 배지>")
        val totalBenefitAmount = discount.getTotalDiscount()
        println(Badge.grantBadge(totalBenefitAmount))
    }

    companion object {
        private const val ORDER_MENUS = "%s %d개"
        private const val NO_BENEFIT = "없음\n"
        private const val ZERO_WON = "0원"
        private const val BEFORE_DISCOUNT_WON = "%d원"
        private const val AFTER_DISCOUNT_TOTAL_WON = "%d원"
        private const val TOTAL_DISCOUNT_WON = "-%d원"
        private const val D_DAY_DISCOUNT = "크리스마스 디데이 할인: -%d원"
        private const val WEEKDAY_DISCOUNT = "평일 할인: -%d원"
        private const val WEEKEND_DISCOUNT = "주말 할인: -%d원"
        private const val SPECIAL_DISCOUNT = "특별 할인: -%d원"
        private const val PRESENTATION_DISCOUNT = "증정 이벤트: -%d원"
        private const val PRESENT_GOODS = "샴페인 1개"
    }
}
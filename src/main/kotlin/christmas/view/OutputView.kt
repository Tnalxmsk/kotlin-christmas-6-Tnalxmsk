package christmas.view

import christmas.model.Badge
import christmas.model.Discount
import christmas.model.EventPresentation
import christmas.model.Order

class OutputView(private val discount: Discount) {
    fun printMenu(order: Order) {
        println("<주문 메뉴>")
        order.getOrderMenus().forEach { menu ->
            println("${menu.menuName} ${menu.count}개")
        }
        println()
    }

    // 할인 전 결제 금액을 출력하고
    fun printBeforeDiscountPrice(totalPrice: Int) {
        println("<할인 전 총주문 금액>")
        println(BEFORE_DISCOUNT_WON.format(totalPrice))
        println()
    }

    // 증정 내용 출력
    fun printPresentationMenu(beforeDiscountTotalPrice: Int) {
        println("<증정 메뉴>")
        if (EventPresentation.checkEventCondition(beforeDiscountTotalPrice)) {
            println(PRESENT_GOODS)
            return
        }
        println(NO_BENEFIT)
        println()
    }

    // 혜택 내용 출력
    fun printBenefitContent() {
        println("<혜택 내역>")
        if (discount.getTotalDiscount() == 0) {
            println(NO_BENEFIT)
            println()
            return
        }
        if (discount.getDDayDiscount() > 0)
            println(D_DAY_DISCOUNT.format(discount.getDDayDiscount()))
        if (discount.getWeekdayDiscount() > 0)
            println(WEEKDAY_DISCOUNT.format(discount.getWeekdayDiscount()))
        if (discount.getWeekendDiscount() > 0)
            println(WEEKEND_DISCOUNT.format(discount.getWeekendDiscount()))
        if (discount.getSpecialDayDiscount() > 0)
            println(SPECIAL_DISCOUNT.format(discount.getSpecialDayDiscount()))
        if (discount.getEvenPresentationDiscount() > 0)
            println(PRESENTATION_DISCOUNT.format(discount.getEvenPresentationDiscount()))
        println()
    }

    // 혜택 받은 금액을 출력
    fun printBenefitPrice() {
        println("<총혜택 금액>")
        when (discount.getTotalDiscount()) {
            0 -> println(ZERO_WON)
            else -> println(TOTAL_DISCOUNT_WON.format(discount.getTotalDiscount() + discount.getEvenPresentationDiscount()))
        }
        println()
    }

    // 할인 후 결제 금액을 출력
    fun printAfterDiscountPrice(totalPrice: Int) {
        println("<할인 후 예상 결제 금액>")
        println(AFTER_DISCOUNT_TOTAL_WON.format(totalPrice - discount.getTotalDiscount()))
        println()
    }

    // 배지를 출력해야겠군
    fun printEventBadge(discount: Discount) {
        println("<12월 이벤트 배지>")
        val totalBenefitAmount = discount.getTotalDiscount() + discount.getEvenPresentationDiscount()
        println(Badge.grantBadge(totalBenefitAmount))
    }

    companion object {
        private const val NO_BENEFIT = "없음"
        private const val ZERO_WON = "0원"
        private const val BEFORE_DISCOUNT_WON = "%d원"
        private const val AFTER_DISCOUNT_TOTAL_WON = "%d원"
        private const val TOTAL_DISCOUNT_WON = "-%d원"
        private const val D_DAY_DISCOUNT = "크리스마스 디데이 할인: -%d원"
        private const val WEEKDAY_DISCOUNT = "평일 할인: -%d원"
        private const val WEEKEND_DISCOUNT = "주말 할인: -%d원"
        private const val SPECIAL_DISCOUNT = "특별 할인: -%d원"
        private const val PRESENTATION_DISCOUNT = "-%d원"
        private const val PRESENT_GOODS = "샴페인 1개"
    }
}
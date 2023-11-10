package christmas.view

import christmas.model.Discount
import christmas.model.Order

class OutputView(private val discount: Discount) {
    fun printMenu(order: Order) {
        println("<주문 메뉴>")
        order.orderMenus.forEach { menu ->
            println("${menu.key} ${menu.value}개")
        }
        println()
    }
    // 할인 전 결제 금액을 출력하고
    fun printBeforeDiscountPrice() {
        println("<할인 전 주문 금액>")
        println()
    }
    // 증정 내용 출력
    fun printPresentationMenu() {
        println("<증정 메뉴>")
        println()
    }
    // 혜택 내용 출력
    fun printBenefitContent() {
        println("<혜택 내역>")
        when {
            discount.getTotalDiscount() == 0 -> println("없음")
            discount.getDDayDiscount() > 0 -> println(D_DAY_DISCOUNT.format(discount.getDDayDiscount()))
        }
        println()
    }

    // 혜택 받은 금액을 출력
    fun printBenefitPrice() {
        println("<총혜택 금액>")
        println()
    }

    // 할인 후 결제 금액을 출력
    fun printAfterDiscountPrice() {
        println("<할인 후 예상 결제 금액>")
        println()
    }
    // 배지를 출력해야겠군
    fun printEventBadge() {
        println("<12월 이벤트 배지>")
        println()
    }

    companion object {
        private const val D_DAY_DISCOUNT = "크리스마스 디데이 할인: -%s원"
    }
}
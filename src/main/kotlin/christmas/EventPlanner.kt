package christmas

import christmas.model.Discount
import christmas.view.InputView
import christmas.view.OutputView

class EventPlanner {
    private val inputView = InputView()

    fun startPlanner() {
        printHello()
        val visitDate = inputView.readDate()
        val order = inputView.readMenu()
        val discount = Discount(visitDate, order)
        val outputView = OutputView(discount)
        printEventInfo()

        with(outputView) {
            printMenu(order)
            printBeforeDiscountPrice()
            printPresentationMenu()
            printBenefitContent()
            printBenefitPrice()
            printAfterDiscountPrice()
            printEventBadge()
        }
    }

    private fun printHello() = println(PLANNER_GREETING)

    private fun printEventInfo() = println(EVENT_INFO)  // 이름 수정하기



    companion object {
        private const val PLANNER_GREETING = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."
        private const val EVENT_INFO = "12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n" // 이름 수정
    }
}
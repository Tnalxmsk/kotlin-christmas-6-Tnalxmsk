package christmas

import christmas.model.Discount
import christmas.model.Price
import christmas.model.VisitDate
import christmas.view.InputView
import christmas.view.OutputView

class EventPlanner {
    private val inputView = InputView()

    fun startPlanner() {
        printHello()
        val visitDate = inputView.readDate()
        val order = inputView.readMenu()
        val price = Price(order)
        val discount = Discount(visitDate, order, price)
        val outputView = OutputView(discount)
        printEventInfo(visitDate)

        with(outputView) {
            printMenu(order)
            printBeforeDiscountPrice(price.getTotalPrice())
            printPresentationMenu(price.getTotalPrice())
            printBenefitContent()
            printBenefitPrice()
            printAfterDiscountPrice(price.getTotalPrice())
            printEventBadge(discount)
        }
    }

    private fun printHello() = println(PLANNER_GREETING)

    private fun printEventInfo(date: VisitDate) = println(EVENT_INFO.format(date.getVisitDate()))  // 이름 수정하기

    companion object {
        private const val PLANNER_GREETING = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."
        private const val EVENT_INFO = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n" // 이름 수정
    }
}
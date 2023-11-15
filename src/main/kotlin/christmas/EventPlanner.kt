package christmas

import christmas.model.Order
import christmas.model.discount.DecemberDiscount
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
        val discount = DecemberDiscount(visitDate, order, price)
        val outputView = OutputView(discount, visitDate, price)

        printAllOutputView(outputView, order)
    }

    private fun printHello() = println(PLANNER_GREETING)

    private fun printAllOutputView(outputView: OutputView, order: Order) {
        with(outputView) {
            printEventInfo()
            printMenu(order)
            printBeforeDiscountPrice()
            printPresentationMenu()
            printBenefitContent()
            printBenefitPrice()
            printAfterDiscountPrice()
            printEventBadge()
        }
    }

    companion object {
        private const val PLANNER_GREETING = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."
    }
}
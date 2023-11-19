package christmas

import christmas.model.Order
import christmas.model.discount.DecemberDiscount
import christmas.model.Price
import christmas.view.InputView
import christmas.view.OutputView

class EventPlannerApp {
    private val inputView = InputView()

    fun startPlanner() {
        println(PLANNER_GREETING)

        val date = inputView.readDate()
        val order = inputView.readMenu()
        val price = Price(order)
        val discount = DecemberDiscount(date, order, price)
        val outputView = OutputView(discount, price)

        printAllOutputView(outputView, order, date.visitDate)
    }

    private fun printAllOutputView(outputView: OutputView, order: Order, date: Int) {
        with(outputView) {
            printDecemberEventView(date)
            printMenu(order)
            printBeforeDiscountPrice()
            printPresentationMenu()
            printBenefitContent(date)
            printBenefitPrice()
            printAfterDiscountPrice()
            printEventBadge()
        }
    }

    companion object {
        private const val PLANNER_GREETING = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."
    }
}
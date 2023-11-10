package christmas

import christmas.view.InputView
import christmas.view.OutputView

class EventPlanner {
    private val inputView = InputView()
    private val outputView = OutputView()

    fun startPlanner() {
        printHello()
        with(inputView) {
            readDate()
            readMenu()
        }
        printEventInfo()
        with(outputView) {
            printMenu()
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
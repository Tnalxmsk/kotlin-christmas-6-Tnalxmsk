package christmas.view

import camp.nextstep.edu.missionutils.Console
import christmas.model.Order
import christmas.extension.toStringToMenuList
import christmas.model.VisitDate

class InputView {
    fun readDate(): VisitDate {
        println(DATE_PROMPT)
        val input = Console.readLine()
        return VisitDate(input.toInt())
    }

    fun readMenu(): Order {
        println(MENU_PROMPT)
        val inputMenu = Console.readLine()
        inputMenu.split(",")
        return Order(inputMenu.toStringToMenuList())
    }

    companion object {
        private const val DATE_PROMPT = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"
        private const val MENU_PROMPT = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"
    }
}
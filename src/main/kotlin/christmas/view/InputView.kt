package christmas.view

import camp.nextstep.edu.missionutils.Console
import christmas.extension.toMenuList
import christmas.extension.toStringList
import christmas.model.Order
import christmas.model.date.VisitDate
import christmas.validation.DateValidator
import christmas.validation.MenuValidator

class InputView {
    private val dateValidator = DateValidator
    private val menuValidator = MenuValidator

    fun readDate(): VisitDate {
        while (true) {
            println(DATE_PROMPT)
            try {
                val input = Console.readLine()
                dateValidator.validateDate(input)
                return VisitDate(input.toInt())
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }

    fun readMenu(): Order {
        while (true) {
            println(MENU_PROMPT)
            try {
                val inputMenu = Console.readLine()
                menuValidator.validateMenus(inputMenu)
                menuValidator.validateMenuBundle(inputMenu.toStringList())
                menuValidator.validateNotContainMenu(inputMenu.toStringList())
                return Order(inputMenu.toMenuList())
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }

    companion object {
        private const val DATE_PROMPT = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"
        private const val MENU_PROMPT = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"
    }
}
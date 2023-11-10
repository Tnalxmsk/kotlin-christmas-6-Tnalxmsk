package christmas.view

import camp.nextstep.edu.missionutils.Console

class InputView {
    fun readDate(): Int {
        println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)")
        val input = Console.readLine()
        // 날짜는 할인을 위해 필요하겠지?
        return 0
    }

    fun readMenu() {
        println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)")
        val inputMenu = Console.readLine()
        // 메뉴를 분리해 맵 형식으로 보내?
    }
}
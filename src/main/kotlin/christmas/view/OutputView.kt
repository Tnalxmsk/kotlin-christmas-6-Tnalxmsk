package christmas.view

class OutputView {
    fun printMenu() {
        println("<주문 메뉴>")
        println()
        // 입력으로 받은 메뉴를 출력
        // Order 클래스에서 구현해보자
    }
    // 할인 전 결제 금액을 출력하고
    fun printBeforeDiscountPrice() {
        println("<할인 전 주문 금액>\n")
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
}
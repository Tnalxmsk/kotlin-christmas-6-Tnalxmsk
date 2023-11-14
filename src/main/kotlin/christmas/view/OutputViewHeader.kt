package christmas.view

enum class OutputViewHeader(val headerView: String) {
    MENU_HEADER("<주문 메뉴>"),
    BEFORE_DISCOUNT_HEADER("<할인 전 총주문 금액>"),
    PRESENTATION_HEADER("<증정 메뉴>"),
    BENEFIT_HEADER("<혜택 내역>"),
    BENEFIT_PRICE_HEADER("<총혜택 금액>"),
    AFTER_DISCOUNT_HEADER("<할인 후 예상 결제 금액>"),
    EVENT_BADGE_HEADER("<12월 이벤트 배지>"),
}
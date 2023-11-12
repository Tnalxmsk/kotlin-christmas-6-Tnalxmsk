package christmas.model.discount

interface DiscountCalculator {
    fun applyDDayDiscount(): Int
    fun applyWeekdayDiscount(): Int
    fun applyWeekendDiscount(): Int
    fun applySpecialDayDiscount(): Int
    fun applyPresentEvent(): Int
}
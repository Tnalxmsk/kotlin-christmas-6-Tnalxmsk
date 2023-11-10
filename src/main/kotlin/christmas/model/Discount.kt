package christmas.model

class Discount(private val date: VisitDate, private val order: Order) {
    private var totalDiscount = 0
    fun applyDDayDiscount(): Int {
        if (date.visitDate > 25) return NOT_DISCOUNT_D_DAY
        val dDayDiscount = DEFAULT_D_DAY_DISCOUNT + (PLUS_D_DAY_DISCOUNT * (date.visitDate - 1))
        totalDiscount += dDayDiscount
        return dDayDiscount
    }

    companion object {
        private const val DEFAULT_D_DAY_DISCOUNT = 1000
        private const val PLUS_D_DAY_DISCOUNT = 100
        private const val NOT_DISCOUNT_D_DAY = 0
    }
}
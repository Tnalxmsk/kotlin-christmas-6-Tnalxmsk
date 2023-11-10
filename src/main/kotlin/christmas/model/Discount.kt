package christmas.model

class Discount(private val date: VisitDate, private val order: Order) {
    private var totalDiscount = 0
    private var dDayDiscount = 0
    init {
        applyDDayDiscount()
    }

    private fun applyDDayDiscount() {
        if (date.getVisitDate() > 25) {
            dDayDiscount =  NOT_DISCOUNT_D_DAY
            return
        }
        dDayDiscount = DEFAULT_D_DAY_DISCOUNT + (PLUS_D_DAY_DISCOUNT * (date.getVisitDate() - 1))
        totalDiscount += dDayDiscount
    }

    fun getDDayDiscount(): Int = dDayDiscount

    fun getTotalDiscount(): Int = totalDiscount

    companion object {
        private const val DEFAULT_D_DAY_DISCOUNT = 1000
        private const val PLUS_D_DAY_DISCOUNT = 100
        private const val NOT_DISCOUNT_D_DAY = 0
    }
}
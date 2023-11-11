package christmas.model

class Discount(
    private val date: VisitDate,
    private val order: Order,
    private val price: Price
) {
    private var totalDiscount = 0
    private var dDayDiscount = 0
    private var weekdayDiscount = 0
    private var weekendDiscount = 0
    private var specialDayDiscount = 0
    private var eventPresentationDiscount = 0

    init {
        applyDDayDiscount()
        applyWeekdayDiscount()
        applyWeekendDiscount()
        applySpecialDayDiscount()
        applyPresentEvent()
    }

    private fun applyDDayDiscount() {
        if (date.isChristmasDDayEvent().not()) {
            dDayDiscount = NOT_DISCOUNT_D_DAY
            return
        }
        dDayDiscount = DEFAULT_D_DAY_DISCOUNT + (PLUS_D_DAY_DISCOUNT * (date.getVisitDate() - 1))
    }

    private fun applyWeekdayDiscount() {
        val menus = order.findDessert()
        if (menus.isEmpty() || date.isWeekday().not()) return
        menus.forEach { menu ->
            weekdayDiscount += menu.count * 2023
        }
    }

    private fun applyWeekendDiscount() {
        val menus = order.findMainDish()
        if (menus.isEmpty() || date.isWeekend().not()) return
        menus.forEach { menu ->
            weekendDiscount += menu.count * 2023
        }
    }

    private fun applySpecialDayDiscount() {
        if (date.isSpecialDay())
            specialDayDiscount += 1000
    }

    private fun applyPresentEvent() {
        if (EventPresentation.checkEventCondition(price.getTotalPrice()))
            eventPresentationDiscount += 25_000
        return
    }

    fun getDDayDiscount(): Int = dDayDiscount

    fun getWeekdayDiscount(): Int = weekdayDiscount

    fun getWeekendDiscount(): Int = weekendDiscount

    fun getSpecialDayDiscount(): Int = specialDayDiscount

    fun getEvenPresentationDiscount(): Int = eventPresentationDiscount

    fun getTotalDiscount(): Int {
        if (date.isWeekend()) {
            totalDiscount = weekendDiscount + dDayDiscount + specialDayDiscount
            return totalDiscount
        }
        totalDiscount = weekdayDiscount + dDayDiscount + specialDayDiscount
        return totalDiscount
    }

    companion object {
        private const val DEFAULT_D_DAY_DISCOUNT = 1000
        private const val PLUS_D_DAY_DISCOUNT = 100
        private const val NOT_DISCOUNT_D_DAY = 0
    }
}
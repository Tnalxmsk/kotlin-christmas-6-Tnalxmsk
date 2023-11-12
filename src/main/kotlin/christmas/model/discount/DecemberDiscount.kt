package christmas.model.discount

import christmas.model.Order
import christmas.model.Price
import christmas.model.VisitDate

class DecemberDiscount(
    private val date: VisitDate,
    private val order: Order,
    private val price: Price
) : DiscountCalculator {
    private var totalDiscount = 0

    override fun applyDDayDiscount(): Int {
        if (date.isChristmasDDayEvent().not()) {
            return NO_DISCOUNT_AMOUNT
        }
        val dDayDiscount = DEFAULT_D_DAY_DISCOUNT + (PLUS_D_DAY_DISCOUNT * (date.getVisitDate() - 1))
        totalDiscount += dDayDiscount
        return dDayDiscount
    }

    override fun applyWeekdayDiscount(): Int {
        val menus = order.findDessert()
        var weekdayDiscount = 0
        if (menus.isEmpty() || date.isWeekday().not()) return NO_DISCOUNT_AMOUNT
        menus.forEach { menu ->
            weekdayDiscount += menu.count * WEEKDAY_DISCOUNT_AMOUNT
        }
        totalDiscount += weekdayDiscount
        return weekdayDiscount
    }

    override fun applyWeekendDiscount(): Int {
        val menus = order.findMainDish()
        var weekendDiscount = 0
        if (menus.isEmpty() || date.isWeekend().not()) return NO_DISCOUNT_AMOUNT
        menus.forEach { menu ->
            weekendDiscount += menu.count * WEEKEND_DISCOUNT_AMOUNT
        }
        totalDiscount += weekendDiscount
        return weekendDiscount
    }

    override fun applySpecialDayDiscount(): Int {
        if (date.isSpecialDay()) {
            totalDiscount += SPECIAL_DAY_DISCOUNT
            return SPECIAL_DAY_DISCOUNT
        }
        return NO_DISCOUNT_AMOUNT
    }

    override fun applyPresentEvent(): Int {
        val presentationGoodsPrice = EventPresentation.getEventPresentationGoodsPrice()
        if (EventPresentation.checkEventCondition(price.getTotalPrice())) {
            totalDiscount += presentationGoodsPrice
            return presentationGoodsPrice
        }
        return NO_DISCOUNT_AMOUNT
    }

    fun getTotalDiscount(): Int {
        return totalDiscount
    }

    companion object {
        private const val DEFAULT_D_DAY_DISCOUNT = 1000
        private const val PLUS_D_DAY_DISCOUNT = 100
        private const val WEEKDAY_DISCOUNT_AMOUNT = 2023
        private const val WEEKEND_DISCOUNT_AMOUNT = 2023
        private const val SPECIAL_DAY_DISCOUNT = 1000
        private const val NO_DISCOUNT_AMOUNT = 0
    }
}
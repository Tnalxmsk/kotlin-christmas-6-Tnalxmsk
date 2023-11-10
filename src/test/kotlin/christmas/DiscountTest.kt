package christmas

import christmas.model.Discount
import christmas.model.Order
import christmas.model.VisitDate
import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat

class DiscountTest {
    @Test
    fun `크리스마스 디데이 할인 적용 확인`() {
        val testDate = VisitDate(24)
        val testOrder = Order(mutableMapOf())
        assertThat(Discount(testDate, testOrder).getDDayDiscount()).isEqualTo(3300)
    }

    @Test
    fun `25일 이후 디데이 할인 적용이 끝났는지 확인`() {
        val testDate = VisitDate(27)
        val testOrder = Order(mutableMapOf())
        assertThat(Discount(testDate, testOrder).getDDayDiscount()).isEqualTo(0)
    }

    @Test
    fun `평일 할인이 적용되는지 확인`() {
        val menus = mutableMapOf<String, Int>()
        menus["초코케이크"] = 1
        menus["아이스크림"] = 2
        val testDate = VisitDate(27)
        val testOrder = Order(menus)
        val discount = Discount(testDate, testOrder)
        assertThat(discount.getWeekdayDiscount()).isEqualTo(6069)
    }

    @Test
    fun `주말 할인이 적용되었는지 확인`() {
        val menus = mutableMapOf<String, Int>()
        menus["티본스테이크"] = 3
        menus["크리스마스파스타"] = 2
        val testDate = VisitDate(30)
        val testOrder = Order(menus)
        val discount = Discount(testDate, testOrder)
        assertThat(discount.getWeekendDiscount()).isEqualTo(6069+4046)
    }

    @Test
    fun `특별 할인이 적용되었는지 확인`() {
        val menus = mutableMapOf<String, Int>()
        menus["티본스테이크"] = 3
        menus["크리스마스파스타"] = 2
        val testDate = VisitDate(31)
        val testOrder = Order(menus)
        val discount = Discount(testDate, testOrder)
        assertThat(discount.getSpecialDayDiscount()).isEqualTo(1000)
    }
}
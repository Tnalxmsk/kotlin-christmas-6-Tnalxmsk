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
}
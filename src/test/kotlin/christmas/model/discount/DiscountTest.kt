package christmas.model.discount

import christmas.model.Order
import christmas.model.Price
import christmas.model.VisitDate
import christmas.model.menu.Menu
import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat

class DiscountTest {
    @Test
    fun `크리스마스 디데이 할인 적용 확인`() {
        val testDate = VisitDate(24)
        val testOrder = Order(mutableListOf())
        val price = Price(testOrder)
        assertThat(DecemberDiscount(testDate, testOrder, price).applyDDayDiscount()).isEqualTo(3300)
    }

    @Test
    fun `25일 이후 디데이 할인 적용이 끝났는지 확인`() {
        val testDate = VisitDate(27)
        val testOrder = Order(mutableListOf())
        val price = Price(testOrder)
        assertThat(DecemberDiscount(testDate, testOrder, price).applyDDayDiscount()).isEqualTo(0)
    }

    @Test
    fun `평일 할인이 적용되는지 확인`() {
        val menus = mutableListOf<Menu>()
        menus.add(Menu("초코케이크", 1))
        menus.add(Menu("아이스크림", 2))
        val testDate = VisitDate(27)
        val testOrder = Order(menus)
        val price = Price(testOrder)
        val discount = DecemberDiscount(testDate, testOrder, price)
        assertThat(discount.applyWeekdayDiscount()).isEqualTo(6069)
    }

    @Test
    fun `주말 할인이 적용되었는지 확인`() {
        val menus = mutableListOf<Menu>()
        menus.add(Menu("티본스테이크", 3))
        menus.add(Menu("크리스마스파스타", 2))
        val testDate = VisitDate(30)
        val testOrder = Order(menus)
        val price = Price(testOrder)
        val discount = DecemberDiscount(testDate, testOrder, price)
        assertThat(discount.applyWeekendDiscount()).isEqualTo(6069 + 4046)
    }

    @Test
    fun `특별 할인이 적용되었는지 확인`() {
        val menus = mutableListOf<Menu>()
        menus.add(Menu("티본스테이크", 3))
        menus.add(Menu("크리스마스파스타", 2))
        val testDate = VisitDate(31)
        val testOrder = Order(menus)
        val price = Price(testOrder)
        val discount = DecemberDiscount(testDate, testOrder, price)
        assertThat(discount.applySpecialDayDiscount()).isEqualTo(1000)
    }

    @Test
    fun `증정 삼품 혜택이 적용되었는지 확인`() {
        val menus = mutableListOf<Menu>()
        menus.add(Menu("티본스테이크", 3))
        menus.add(Menu("크리스마스파스타", 2))
        val testDate = VisitDate(30)
        val testOrder = Order(menus)
        val price = Price(testOrder)
        val discount = DecemberDiscount(testDate, testOrder, price)
        assertThat(discount.applyPresentEvent()).isEqualTo(25000)
    }

    @Test
    fun `모든 혜택이 잘 적용되었는지 확인`() {
        val menus = mutableListOf<Menu>()
        menus.add(Menu("티본스테이크", 3))
        menus.add(Menu("크리스마스파스타", 3))
        val testOrder = Order(menus)
        var result = 0
        val discount = DecemberDiscount(VisitDate(23), Order(menus), Price(testOrder))
        result += discount.applyDDayDiscount()
        result += discount.applyWeekdayDiscount()
        result += discount.applyWeekendDiscount()
        result += discount.applySpecialDayDiscount()
        result += discount.applyPresentEvent()
        assertThat(result).isEqualTo(40338)
    }
}
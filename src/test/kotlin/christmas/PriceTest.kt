package christmas

import christmas.model.Order
import christmas.model.Price
import christmas.model.menu.Menu
import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat

class PriceTest {
    @Test
    fun `비어있는 메뉴 카테고리가 있을 경우에도 총가격에 값이 저장되는지 확인`() {
        val menus = mutableListOf<Menu>()
        menus.add(Menu("아이스크림", 3))
        menus.add(Menu("크리스마스파스타", 2))
        val price = Price(Order(menus))
        assertThat(price.getTotalPrice()).isEqualTo(65000)
    }

    @Test
    fun `할인 전 총금액을 제대로 반환하는지 확인`() {
        val menus = mutableListOf<Menu>()
        menus.add(Menu("아이스크림", 3))
        menus.add(Menu("크리스마스파스타", 2))
        menus.add(Menu("레드와인", 2))
        menus.add(Menu("시저샐러드", 4))
        val price = Price(Order(menus))
        assertThat(price.getTotalPrice()).isEqualTo(217000)
    }
}
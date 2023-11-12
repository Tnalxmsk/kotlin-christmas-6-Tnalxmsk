package christmas.model

import christmas.model.Order
import christmas.model.menu.Menu
import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat

class OrderTest {
    @Test
    fun `주문 목록에 디저트 메뉴가 있는지 확인인 후 반환`() {
        val menus = mutableListOf<Menu>()
        menus.add(Menu("초코케이크", 1))
        menus.add(Menu("아이스크림", 2))
        val dessertsTest = mutableListOf<Menu>()
        dessertsTest.add(Menu("초코케이크", 1))
        dessertsTest.add(Menu("아이스크림", 2))

        val order = Order(menus)

        assertThat(order.findDessert()).isEqualTo(dessertsTest)
    }
}
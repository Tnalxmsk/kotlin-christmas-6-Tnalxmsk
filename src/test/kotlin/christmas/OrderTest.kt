package christmas

import christmas.model.Order
import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat

class OrderTest {
    @Test
    fun `주문 목록에 디저트 메뉴가 있는지 확인인 후 반환`() {
        val menus = mutableMapOf<String, Int>()
        menus["초코케이크"] = 2
        menus["아이스크림"] = 3
        val dessertsTest = mutableMapOf<String, Int>()
        dessertsTest["초코케이크"] = 2
        dessertsTest["아이스크림"] = 3

        val order = Order(menus)

        assertThat(order.findDessert()).isEqualTo(dessertsTest)
    }
}
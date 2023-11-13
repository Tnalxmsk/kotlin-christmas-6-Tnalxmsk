package christmas.model.validation

import christmas.validation.MenuValidator
import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat

class MenuValidatorTest {
    @Test
    fun `중복 요소가 있으면 true를 반환하는지 확인`() {
        val input = "스테이크-1,스테이크-2,샴페인-3,제로콜라-2".split(",")
        assertThat(MenuValidator.hasDuplicationMenu(input)).isEqualTo(true)
    }

    @Test
    fun `주문 개수가 20개가 넘으면 true를 반환하는지 확인`() {
        val input = "스테이크-1,스테이크-2,물-25,제로콜라-10".split(",")
        assertThat(MenuValidator.isOverCount(input)).isEqualTo(true)
    }

    @Test
    fun `주문 개수에 문자열이 포함되어 있으면 true를 반환하는지 확인`() {
        val input = "스테이크-1,제로콜라-a".split(",")
        assertThat(MenuValidator.hasCountCharacter(input)).isEqualTo(true)
    }

    @Test
    fun `-이 없으면 true를 반환하는지 확인`() {
        val input = "스테이크-1,제로콜라a".split(",")
        assertThat(MenuValidator.isNotContainHyphen(input)).isEqualTo(true)
    }

    @Test
    fun `포함되어 있지 않은 메뉴가 있으면 예외를 날림`() {
        assertThat(MenuValidator.isNotContainMenu("까르보나라-3")).isEqualTo(true)
    }
}
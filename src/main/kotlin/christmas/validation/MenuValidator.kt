package christmas.validation

import christmas.extension.toCountList
import christmas.extension.toMenuNameList
import christmas.model.menu.*

enum class MenuValidator(val errorMessage: String) {
    EMPTY_MENU("[ERROR] 메뉴를 입력하지 않았습니다. 다시 입력해 주세요."),
    INCLUDE_GAP("[ERROR] 공백이 포함되어 있습니다. 다시 입력해 주세요."),
    INVALID_MENU("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."),
    ONLY_BEVERAGE("[ERROR] 음료만 주문하실 수 없습니다. 다시 입력해 주세요"),
    OVER_MENU_COUNT("[ERROR] 메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다. 다시 입력해 주세요.");

    companion object {
        fun validateMenus(input: String) {
            val error = when {
                input.isEmpty() -> EMPTY_MENU
                input.contains(" ") -> INCLUDE_GAP
                input.contains(",,") -> INVALID_MENU
                else -> return
            }
            throw IllegalArgumentException(error.errorMessage)
        }

        fun validateMenuBundle(input: List<String>) {
            val error = when {
                isNotContainHyphen(input) -> INVALID_MENU
                hasDuplicationMenu(input) -> INVALID_MENU
                hasCountCharacter(input) -> INVALID_MENU
                isOverCount(input) -> OVER_MENU_COUNT
                isOnlyBeverage(input) -> ONLY_BEVERAGE
                else -> return
            }
            throw IllegalArgumentException(error.errorMessage)
        }

        fun validateNotContainMenu(input: List<String>) {
            input.toMenuNameList().forEach { menu ->
                if (isNotContainMenu(menu)) {
                    throw IllegalArgumentException(INVALID_MENU.errorMessage)
                } else return
            }
        }

        internal fun hasDuplicationMenu(input: List<String>): Boolean {
            val menus = input.toMenuNameList()
            return menus.size != menus.toSet().size
        }

        internal fun isOverCount(input: List<String>): Boolean {
            var totalCount = 0
            for (count in input.toCountList()) {
                totalCount += count.toInt()
            }
            return totalCount > 20
        }

        internal fun hasCountCharacter(input: List<String>): Boolean {
            input.toCountList().forEach {
                if (it.matches(Regex("\\d+")).not())
                    return true
            }
            return false
        }

        internal fun isNotContainHyphen(input: List<String>): Boolean {
            for (menu in input) {
                if (!menu.contains("-")) {
                    return true
                }
            }
            return false
        }

        internal fun isNotContainMenu(menu: String): Boolean {
            val menuChecker = MenuChecker
            return when {
                menuChecker.isDessert(menu) -> false
                menuChecker.isAppetizer(menu) -> false
                menuChecker.isBeverage(menu) -> false
                menuChecker.isMainDish(menu) -> false
                else -> true
            }
        }

        internal fun isOnlyBeverage(menus: List<String>): Boolean {
            var count = 0
            val menuChecker = MenuChecker
            menus.toMenuNameList().forEach {
                when {
                    menuChecker.isDessert(it) -> count++
                    menuChecker.isAppetizer(it) -> count++
                    menuChecker.isMainDish(it) -> count++
                }
            }
            return count == 0
        }
    }
}
package christmas.validation

enum class DateValidator(val error: String) {
    EMPTY_DATE("[ERROR] 날짜를 입력하지 않았습니다. 다시 입력해 주세요."),
    INCLUDE_GAP("[ERROR] 공백이 포함되어 있습니다. 다시 입력해 주세요."),
    INCLUDE_CHARACTER("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    OUT_OF_RANGE("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");

    companion object {
        private const val FIRST_DAY = 1
        private const val LAST_DAY = 31

        fun validateDate(input: String) {
            val error = when {
                input.isEmpty() -> EMPTY_DATE
                input.contains(" ") -> INCLUDE_GAP
                input.matches(Regex("\\d+")).not() -> INCLUDE_CHARACTER
                input.toInt() !in (FIRST_DAY..LAST_DAY) -> OUT_OF_RANGE
                else -> return
            }
            throw IllegalArgumentException(error.error)
        }
    }
}
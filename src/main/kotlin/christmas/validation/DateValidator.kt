package christmas.validation

enum class DateValidator(val error: String) {
    EMPTY_DATE("[ERROR] 날짜를 입력하지 않았습니다. 다시 입력해 주세요."),
    INCLUDE_SPACE("[ERROR] 공백이 포함되어 있습니다. 다시 입력해 주세요."),
    INCLUDE_CHARACTER("[ERROR] 날짜는 숫자만 입력 가능합니다. 다시 입력해 주세요."),
    OUT_OF_RANGE("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");

    companion object {
        fun validateDate(input: String) {
            val error = when {
                input.isEmpty() -> EMPTY_DATE
                input.contains(" ") -> INCLUDE_SPACE
                input.matches(Regex("\\d+")).not() -> INCLUDE_CHARACTER
                input.toInt() !in (1..31) -> OUT_OF_RANGE
                else -> return
            }
            throw IllegalArgumentException(error.error)
        }
    }
}
package christmas.model.date

object DateChecker {
    private const val FIRST_DAY = 1
    private const val SECOND_DAY = 2
    private const val THIRD_DAY = 3
    private const val LAST_DAY = 31
    private const val WEEK = 7
    private const val CHRISTMAS = 25

    fun isWeekday(date: Int): Boolean {
        val weekDay = (FIRST_DAY..LAST_DAY) -
                (FIRST_DAY..LAST_DAY step WEEK) -
                (SECOND_DAY..LAST_DAY step WEEK)
        return weekDay.contains(date)
    }

    fun isWeekend(date: Int): Boolean {
        val weekend = (FIRST_DAY..LAST_DAY step WEEK) + (SECOND_DAY..LAST_DAY step WEEK)
        return weekend.contains(date)
    }

    fun isSpecialDay(date: Int): Boolean {
        val specialDay = (THIRD_DAY..LAST_DAY step WEEK) + CHRISTMAS
        return specialDay.contains(date)
    }

    fun isDDayEvent(date: Int): Boolean {
        return date <= CHRISTMAS
    }
}
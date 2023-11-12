package christmas.model

class VisitDate(private val visitDate: Int) {
    fun getVisitDate() = visitDate

    fun isWeekday(): Boolean {
        val weekDay = (FIRST_DAY..LAST_DAY) -
                (FIRST_DAY..LAST_DAY step WEEK) -
                (SECOND_DAY..LAST_DAY step WEEK)
        return weekDay.contains(visitDate)
    }

    fun isWeekend(): Boolean {
        val weekend = (FIRST_DAY..LAST_DAY step WEEK) + (SECOND_DAY..LAST_DAY step WEEK)
        return weekend.contains(visitDate)
    }

    fun isSpecialDay(): Boolean {
        val specialDay = (THIRD_DAY..LAST_DAY step WEEK) + CHRISTMAS
        return specialDay.contains(visitDate)
    }

    fun isChristmasDDayEvent(): Boolean {
        return visitDate <= CHRISTMAS
    }

    companion object {
        private const val FIRST_DAY = 1
        private const val SECOND_DAY = 2
        private const val THIRD_DAY = 3
        private const val LAST_DAY = 31
        private const val WEEK = 7
        private const val CHRISTMAS = 25
    }
}
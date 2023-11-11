package christmas.model

class VisitDate(private val visitDate: Int) {
    fun getVisitDate() = visitDate

    fun isWeekday(): Boolean {
        val weekDay = (1..31) - (1..31 step 7) - (2..31 step 7)
        return weekDay.contains(visitDate)
    }

    fun isWeekend(): Boolean {
        val weekend = (1..31 step 7) + (2..31 step 7)
        return weekend.contains(visitDate)
    }

    fun isSpecialDay(): Boolean {
        val specialDay = (3..31 step 7) + 25
        return specialDay.contains(visitDate)
    }

    fun isChristmasDDayEvent(): Boolean {
        return visitDate < 25
    }
}
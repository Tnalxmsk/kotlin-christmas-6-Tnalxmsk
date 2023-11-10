package christmas.model

class VisitDate(private val visitDate: Int) {
    fun getVisitDate() = visitDate

    fun isWeekday(): Boolean {
        val weekDay = (1..31) - (1..30 step 7)
        println(weekDay)
        return weekDay.contains(visitDate)
    }
}
package christmas.model

class VisitDate(private val visitDate: Int) {
    // 각 날짜를 출력 추후 삭제
    fun getVisitDate() = visitDate

    fun isWeekday(): Boolean {
        val weekDay = (1..31) - (1..31 step 7) - (2..31 step 7)
        println(weekDay)
        return weekDay.contains(visitDate)
    }

    fun isWeekend(): Boolean {
        val weekend = (1..31 step 7) + (2..31 step 7)
        println(weekend)
        return weekend.contains(visitDate)
    }

    fun isSpecialDay(): Boolean {
        val specialDay = (3..31 step 7) + 25
        println(specialDay)
        return specialDay.contains(visitDate)
    }

    fun isChristmasDDayEvent(): Boolean {
        return visitDate < 25
    }
}
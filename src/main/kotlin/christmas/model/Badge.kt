package christmas.model

enum class Badge(val badgeName: String) {
    STAR("별"),
    TREE("트리"),
    SANTA("산타");

    companion object {
        fun grantBadge(totalBenefit: Int): String {
            return when {
                totalBenefit >= 20_000 -> SANTA.badgeName
                totalBenefit >= 10_000 -> TREE.badgeName
                else -> STAR.badgeName
            }
        }
    }
}
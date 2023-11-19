package christmas.model

enum class Badge(val badgeName: String) {
    STAR("별"),
    TREE("트리"),
    SANTA("산타"),
    NONE("없음");

    companion object {
        private const val SANTA_BADGE_AMOUNT = 20_000
        private const val TREE_BADGE_AMOUNT = 10_000
        private const val STAR_BADGE_AMOUNT = 5000

        fun grantBadge(totalBenefit: Int): String {
            return when {
                totalBenefit >= SANTA_BADGE_AMOUNT -> SANTA.badgeName
                totalBenefit >= TREE_BADGE_AMOUNT -> TREE.badgeName
                totalBenefit >= STAR_BADGE_AMOUNT -> STAR.badgeName
                else -> NONE.badgeName
            }
        }
    }
}
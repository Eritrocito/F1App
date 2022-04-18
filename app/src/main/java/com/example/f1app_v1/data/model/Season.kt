package com.example.f1app_v1.data.model



data class SeasonId (
    val id: String = "",
)
data class SeasonIdList(val stages: List<SeasonId> =listOf())

data class Season(

    val stage: Stage
) {
    inner class Stage(
        val competitors: List<Comp>
    ) {
        inner class Comp(
            val id: String = "",
            val name:String=""
        )
    }
}


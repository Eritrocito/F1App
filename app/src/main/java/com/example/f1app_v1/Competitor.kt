
package com.example.f1app_v1
/*
/*data class Season(
    val id: String = "",
)
data class SeasonList(val stages: List<Season> =listOf())*/

data class Season2(

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


data class Info(
    val placeofbirth: String = "",
    val country_code_of_residence: String = "",
    val weight: Int = -1


)

data class Competitor(
    val info: Info
)*/




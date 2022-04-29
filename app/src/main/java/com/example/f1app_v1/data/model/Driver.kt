package com.example.f1app_v1.data.model

data class DriverBaseInfo(

    val stage: Stage
) {
    inner class Stage(
        val competitors: List<Comp>
    ) {
        inner class Comp(
            val id: String = "",
            val name: String = "",
            val nationality: String="",
            val result:Result
        ){
            inner class Result(
                val points:Int,
                val position:Int
            )
        }
    }
}


data class Driver(
    val competitor: Competitor,
    val info: Info?,
    val teams:List<Team>

) {
    inner class Competitor(
        val name: String = ""
       // val nationality: String = ""
    )

    inner class Team(
        val name:String="",
        val nationality: String
    )

    inner class Info(
        val placeofbirth: String? = "",
        val dateofbirth: String? = "",
        val country_of_residence: String? = "",
        val salary: Int? = -1,
        val debut: String? = "",
        val first_victory: String? = "",
        val wcs_won: Int? = -1
    )
}



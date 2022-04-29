package com.example.f1app_v1.data.model

data class TeamBaseInfo(
    val stage: Stage
) {
    inner class Stage(
        val teams: List<Team>
    ) {
        inner class Team(
            val id: String,
            val name: String,
            val nationality: String,
            val result: Result
        ) {
            inner class Result(
                val points: Int,
                val position: Int
            )
        }
    }
}

data class Team(
    val team: Team,
    val competitors: List<Comp>,
    val info: Info?
) {
    inner class Team(
        val name: String
    )

    inner class Comp(
        val name: String, val nationality: String
    )

    inner class Info(
        val country_of_residence: String,
        val debut: String,
        val foundation_year: String
    )
}
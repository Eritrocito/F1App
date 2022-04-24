package com.example.f1app_v1.data.model

class RaceBaseInfo(
    val stage: Stage
) {
    inner class Stage(
        val stages: List<Race>
    ) {
        inner class Race(
            val id: String = "",
            val description: String = "",
            val scheduled: String = "",
            val scheduled_end: String = "",
            val status:String="",
            val venue: Venue
        ) {
            inner class Venue(
                val name:String="",
                val city:String="",
                val country:String="",
                val length:Int=-1,
                val debut:Int=-1,
                val laps:Int=-1
            )
        }
    }
}
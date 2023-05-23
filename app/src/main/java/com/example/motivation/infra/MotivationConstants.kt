package com.example.motivation.infra

class MotivationConstants private constructor(){ // private constructor faz com que ninguem possa intanciar essa classe

    object KEY {
        const val USER_NAME = "USER_NAME"
    }

    object FILTER {
        const val ALL = 1
        const val HAPPY = 2
        const val SUNNY = 3
    }

}
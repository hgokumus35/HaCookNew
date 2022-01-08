package com.moralabs.hacooknew.domain.entity

data class Food(
    var id : Int? = null,
    var title : String? = null,
    var summary : String? = null,
    var dishTypes : String? = null,
    var image : String? = null,
    var readyInMinutes : Int? = null,
    var saved : Boolean? = null
) : Entity()
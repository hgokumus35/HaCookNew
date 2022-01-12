package com.moralabs.hacooknew.domain.entity

class HomeEntity(
    val todaysFood : Food?,
    val randomFood : List<Food>,
    val collectionsHome : List<Food>
) : Entity()


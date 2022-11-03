package com.octopus.moviesapp.android.usecases.mapper

abstract class Mapper<I, O> {
     abstract fun map(input: I): O
     open fun mapList(input: List<I>): List<O> {
         return if (input.isEmpty()) emptyList()
         else input.map {
             map(it)
         }
     }
}
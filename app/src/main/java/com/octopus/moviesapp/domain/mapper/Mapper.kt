package com.octopus.moviesapp.domain.mapper

interface Mapper<I, O> {
    fun map(input: I): O
}
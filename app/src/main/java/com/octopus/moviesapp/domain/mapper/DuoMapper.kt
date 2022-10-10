package com.octopus.moviesapp.domain.mapper

interface DuoMapper<I1, I2, O> {
    fun map(firstInput: I1, secondInput: I2): O
}
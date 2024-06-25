package com.example.gradesearch.data

import kotlinx.coroutines.flow.Flow

interface GradeSearchRepo {

    fun getGradeByInputStream(input: String): Flow<List<Result>>
    fun getGradeByCode(input: String): Result?
    fun getAllGradeStream(currentId: Int): Flow<List<Result>>
    fun getGradeByName(input: String): Flow<List<Result>>
    fun getPointer(currentId: Float): Flow<List<Result>>
}
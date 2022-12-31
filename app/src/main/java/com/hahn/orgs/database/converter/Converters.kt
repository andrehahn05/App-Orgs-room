package com.hahn.orgs.database.converter

import androidx.room.TypeConverter
import java.math.BigDecimal

class Converters {
    @TypeConverter
    fun deDouble(value: Double?) : BigDecimal {
        return value?.let { BigDecimal(value.toString()) } ?: BigDecimal.ZERO
    }
    
    @TypeConverter
    fun bigDecimalForDouble(value: BigDecimal?) :Double? {
        return  value?.let {value.toDouble() }
    }
}
package com.beans.cmstest.data.localdb



import androidx.databinding.adapters.Converters
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "beans")
data class BeansEntity(
    @PrimaryKey val beanId: Int,
    @TypeConverters(Converters::class)
    val groupName: List<String>,
    @TypeConverters(Converters::class)
    val ingredients: List<String>,
    val flavorName: String,
    val description: String,
    val colorGroup: String,
    val backgroundColor: String,
    val imageUrl: String,
    val glutenFree: Boolean,
    val sugarFree: Boolean,
    val seasonal: Boolean,
    val kosher: Boolean
)
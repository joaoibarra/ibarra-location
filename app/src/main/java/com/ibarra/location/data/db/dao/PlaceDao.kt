package com.ibarra.location.data.db.dao

import androidx.paging.DataSource
import androidx.room.*
import com.ibarra.location.data.db.entity.Place

@Dao
interface PlaceDao {
    @Query("SELECT * FROM places ORDER BY created ASC")
    fun findAll(): DataSource.Factory<Int, Place>

    @Query("SELECT * FROM places WHERE id LIKE :placeId ORDER BY created ASC")
    fun findByPlaceId(placeId: String): DataSource.Factory<Int, Place>

    @Query("SELECT * FROM places WHERE type LIKE :type ORDER BY created ASC")
    fun findPlaceByType(type: String): DataSource.Factory<Int, Place>

    @Query("SELECT * FROM places WHERE id LIKE :placeId ORDER BY created ASC")
    fun findById(placeId: String): Place

    @Query("SELECT * FROM places WHERE type LIKE :type ORDER BY created ASC")
    fun findByType(type: String): Place

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(article: Place)

    @Delete
    fun delete(article: Place)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(sources: List<Place>?)
}
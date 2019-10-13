package com.ibarra.location.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.ibarra.location.data.db.converter.DateConverter
import com.ibarra.location.data.remote.domain.NearbyPlaceRepository
import java.util.*

@Entity(tableName = "places")
@TypeConverters(DateConverter::class)
data class Place (
    @PrimaryKey(autoGenerate = false) val id: String,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "openNow") val openNow: Boolean?,
    @ColumnInfo(name = "latitude") val latitude: Double?,
    @ColumnInfo(name = "longitude") val longitude: Double?,
    @ColumnInfo(name = "icon") val icon: String?,
    @ColumnInfo(name = "placeId") val placeId: String?,
    @ColumnInfo(name = "rating") val rating: Double?,
    @ColumnInfo(name = "userRatingsTotal") val userRatingsTotal: Int?,
    @ColumnInfo(name = "photo") val photo: String?,
    @ColumnInfo(name = "type") val type: String?,
    @ColumnInfo(name = "vicinity") val vicinity: String?,
    @ColumnInfo(name = "created") val created: Date
){
    companion object {
        fun to(nearbyPlaceRepository: NearbyPlaceRepository, category: String): Place {
            return Place (
                id = nearbyPlaceRepository.id,
                name = nearbyPlaceRepository.name,
                openNow = nearbyPlaceRepository.openingHours?.openNow,
                latitude = nearbyPlaceRepository.geometry?.location?.latitude,
                longitude = nearbyPlaceRepository.geometry?.location?.longitude,
                icon = nearbyPlaceRepository.icon,
                placeId = nearbyPlaceRepository.place_id,
                rating = nearbyPlaceRepository.rating,
                userRatingsTotal = nearbyPlaceRepository.userRatingsTotal,
                photo = nearbyPlaceRepository.photos?.get(0)?.photoReference,
                vicinity = nearbyPlaceRepository.vicinity,
                type = category,
                created = Date()
            )
        }

        fun toList(nearbyPlaces: List<NearbyPlaceRepository>?, category: String): List<Place>? {
            return nearbyPlaces?.map { to(it, category) }
        }
    }
}
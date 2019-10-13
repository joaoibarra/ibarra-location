package com.ibarra.location.dao.place


import com.ibarra.location.data.remote.domain.*

val placeRepository = NearbyPlaceRepository (
    geometry = GeometryRepository(NearbyLocationRepository(-23.5707133, -46.66112409999999)),
    icon = "https://maps.gstatic.com/mapfiles/place_api/icons/cafe-71.png",
    id = "5c4e127f10e70b969c78927d9634bc5b70753770",
    name = "Empada de Minas",
    place_id = "ChIJK3vkNttZzpQR-3f0aSGlWME",
    rating = 4.399999999999999,
    userRatingsTotal = 278,
    vicinity = "Rua Pamplona, 1704 - Jardim Paulista, São Paulo",
    openingHours = OpeningHoursRepository(true),
    photos = listOf(
        PhotoRepository(
            651.0,
            1259.0,
            "CmRaAAAAkVTvdE9hw7bC_koXdnxWYTH-L-eiRFL7e1YP5zv346KV9mXVkPTzlLMMaCd6MRvxrj_Nyw64V6pfxAd-UeiOtEmp9bUWOjzX6D-E7Q_5TC1Co_KYK52aF5vx0VZaU5VeEhD6CkdzeCrPbxQkqdgittkXGhTCh3uL1ix0CMbabHDXoNRw8DXCvQ",
            listOf("\\u003ca href=\\\"https://maps.google.com/maps/contrib/112939575084307822557/photos\\\"\\u003eEmpada de Minas\\u003c/a\\u003e")))
)
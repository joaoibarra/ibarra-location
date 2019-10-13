package com.ibarra.location.place

import android.content.Intent
import androidx.test.rule.ActivityTestRule
import br.com.concretesolutions.kappuccino.assertions.VisibilityAssertions
import br.com.concretesolutions.kappuccino.custom.intent.IntentMatcherInteractions
import br.com.concretesolutions.kappuccino.custom.recyclerView.RecyclerViewInteractions
import com.ibarra.location.R
import com.ibarra.location.extensions.loadResponse
import com.ibarra.location.ui.places.PlaceActivity
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest

fun arrange(rule: ActivityTestRule<PlaceActivity>, func: PlaceActivityArrangeRobot.() -> Unit) = PlaceActivityArrangeRobot(
    rule
).apply { func() }
fun act(func: PlaceActivityActRobot.() -> Unit) = PlaceActivityActRobot().apply { func() }
fun assert(func: PlaceActivityAssertRobot.() -> Unit) = PlaceActivityAssertRobot().apply { func() }

class PlaceActivityArrangeRobot(private val rule: ActivityTestRule<PlaceActivity>) {

    private val sourceResponse by lazy { "places.json".loadResponse() }

    fun launchActivity() {
        rule.launchActivity(Intent())
    }

    fun mockWebServer(mockWebServer: MockWebServer) {
        val dispatcher = object : Dispatcher() {
            @Throws(InterruptedException::class)
            override fun dispatch(request: RecordedRequest): MockResponse {
                return when {
                    request.path.orEmpty().contains("sources") -> {
                        MockResponse().setResponseCode(200).setBody(sourceResponse)
                    }
                    else -> MockResponse().setResponseCode(404)
                }
            }
        }

        mockWebServer.dispatcher = dispatcher
    }
}

class PlaceActivityActRobot {
    fun clickPlaceItemByPosition(position: Int) {
        RecyclerViewInteractions.recyclerView(R.id.rv_places) {
            atPosition(position) {
                click()
            }
        }
    }
}

class PlaceActivityAssertRobot {
    fun isPlaceListVisible() {
        VisibilityAssertions.displayed {
            id(R.id.rv_places)
        }
    }

    fun verifyItemByPosition(position: Int = 0) {
        RecyclerViewInteractions.recyclerView(R.id.rv_places) {
            atPosition(position) {
                displayed {
                    id(R.id.place_name)
                    id(R.id.place_rating)
                }
            }
        }
    }
}
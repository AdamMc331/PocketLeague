package com.adammcneilly.pocketleague.teamlist.data.remote

import android.util.Log
import com.adammcneilly.pocketleague.core.data.Result
import com.adammcneilly.pocketleague.core.data.remote.liquipedia.LiquipediaRetrofitAPI
import com.adammcneilly.pocketleague.core.domain.models.Team
import com.adammcneilly.pocketleague.teamlist.data.TeamListService
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.StringReader
import javax.inject.Inject

class LiquipediaTeamListService @Inject constructor(
    private val api: LiquipediaRetrofitAPI,
) : TeamListService {
    private val tag = "LiquipediaTeamService"

    override suspend fun fetchAllTeams(): Result<List<Team>> {
        val liquipediaResponse = api.fetchTeamsPage()

        val body = liquipediaResponse.body()?.parse?.text?.x

        val teams = mutableListOf<Team>()

        if (body != null) {
            val factory = XmlPullParserFactory.newInstance().apply {
                isNamespaceAware = true
            }
            val xpp = factory.newPullParser()
            xpp.setInput(StringReader(body))

            var eventType = xpp.eventType
            while (eventType != XmlPullParser.END_DOCUMENT) {
                when (eventType) {
                    XmlPullParser.START_DOCUMENT -> Log.d(tag, "Start Document")
                    XmlPullParser.START_TAG -> {
                        Log.d(tag, "Start tag ${xpp.name}")
                        if (xpp.attributeCount > 0) {
                            val firstAttributeName = xpp.getAttributeName(0)
                            val firstAttributeValue = xpp.getAttributeValue(0)

                            if (firstAttributeName == "class" && firstAttributeValue == "team-template-text") {
                                // Move to link
                                xpp.next()
                                // Move to text
                                xpp.next()

                                teams.add(
                                    Team(
                                        name = xpp.text,
                                        logoImageUrl = "",
                                        roster = emptyList(),
                                    )
                                )
                            }
                        }
                    }
                    XmlPullParser.END_TAG -> Log.d(tag, "End tag ${xpp.name}")
                    XmlPullParser.TEXT -> Log.d(tag, "Text ${xpp.text}")
                }

                eventType = xpp.next()
            }

            Log.d(tag, "End Document")
        }

        return Result.Success(teams.toList())
    }
}

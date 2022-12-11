package com.adammcneilly.pocketleague.data.team

object MockTeamListResponse {
    val json = """
        {
          "teams": [
            {
              "team": {
                "_id": "6332a14dda9d7ca1c7bb4688",
                "slug": "4688-1s",
                "name": "1s",
                "region": "EU",
                "relevant": true
              },
              "players": [
                {
                  "_id": "5f3d8fdd95f40596eae24044",
                  "slug": "4044-hyderr",
                  "tag": "Hyderr",
                  "name": "Giovanni Spagnuolo",
                  "country": "it",
                  "team": {
                    "_id": "6332a14dda9d7ca1c7bb4688",
                    "slug": "4688-1s",
                    "name": "1s",
                    "region": "EU",
                    "relevant": true
                  },
                  "accounts": [
                    {
                      "platform": "steam",
                      "id": "76561198301226130"
                    }
                  ],
                  "relevant": true
                },
                {
                  "_id": "5f3d8fdd95f40596eae244b9",
                  "slug": "44b9-revezy",
                  "tag": "Revezy",
                  "name": "Joshua Wright",
                  "country": "en",
                  "team": {
                    "_id": "6332a14dda9d7ca1c7bb4688",
                    "slug": "4688-1s",
                    "name": "1s",
                    "region": "EU",
                    "relevant": true
                  },
                  "accounts": [
                    {
                      "platform": "steam",
                      "id": "76561199011842266"
                    }
                  ],
                  "relevant": true
                },
                {
                  "_id": "5f3d8fdd95f40596eae2403c",
                  "slug": "403c-dead",
                  "tag": "dead.",
                  "name": "Lorenzo Tongiorgi",
                  "country": "it",
                  "team": {
                    "_id": "6332a14dda9d7ca1c7bb4688",
                    "slug": "4688-1s",
                    "name": "1s",
                    "region": "EU",
                    "relevant": true
                  },
                  "accounts": [
                    {
                      "platform": "steam",
                      "id": "76561198159911196"
                    }
                  ],
                  "relevant": true
                },
                {
                  "_id": "614ad971143c37878b237a28",
                  "slug": "7a28-laru",
                  "tag": "Laru",
                  "country": "it",
                  "team": {
                    "_id": "6332a14dda9d7ca1c7bb4688",
                    "slug": "4688-1s",
                    "name": "1s",
                    "region": "EU",
                    "relevant": true
                  },
                  "accounts": [
                    {
                      "platform": "steam",
                      "id": "76561198222753740"
                    }
                  ],
                  "coach": true
                }
              ]
            }
          ]
        }
    """.trimIndent()
}

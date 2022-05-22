package com.adammcneilly.pocketleague.core.data.remote

val eventListResponseJson = """
    {
      "events": [
        {
          "_id": "614b6485f8090ec745286425",
          "slug": "6425-rlcs-2021-22-world-championship",
          "name": "RLCS 2021-22 World Championship",
          "startDate": "2022-08-03T23:00:00Z",
          "endDate": "2022-08-14T22:30:00Z",
          "region": "INT",
          "mode": 3,
          "prize": {
            "amount": 2085000,
            "currency": "USD"
          },
          "tier": "S",
          "image": "https://griffon.octane.gg/events/rlcs-2021-22.png",
          "groups": [
            "rlcs",
            "rlcs2122"
          ],
          "stages": [
            {
              "_id": 0,
              "name": "Wildcard",
              "region": "INT",
              "startDate": "2022-08-03T23:00:00Z",
              "endDate": "2022-08-07T22:30:00Z",
              "prize": {
                "amount": 85000,
                "currency": "USD"
              },
              "liquipedia": "https://liquipedia.net/rocketleague/Rocket_League_Championship_Series/2021-22/Wildcard",
              "lan": true,
              "location": {
                "venue": "Dickies Arena",
                "city": "Fort Worth",
                "country": "us"
              }
            },
            {
              "_id": 1,
              "name": "Group Stage",
              "region": "INT",
              "startDate": "2022-08-08T23:00:00Z",
              "endDate": "2022-08-12T22:30:00Z",
              "prize": {
                "amount": 200000,
                "currency": "USD"
              },
              "liquipedia": "https://liquipedia.net/rocketleague/Rocket_League_Championship_Series/2021-22/Group_Stage",
              "lan": true,
              "location": {
                "venue": "Dickies Arena",
                "city": "Fort Worth",
                "country": "us"
              }
            },
            {
              "_id": 2,
              "name": "Playoffs",
              "region": "INT",
              "startDate": "2022-08-12T23:30:00Z",
              "endDate": "2022-08-14T22:30:00Z",
              "prize": {
                "amount": 1800000,
                "currency": "USD"
              },
              "liquipedia": "https://liquipedia.net/rocketleague/Rocket_League_Championship_Series/2021-22/Playoffs",
              "lan": true,
              "location": {
                "venue": "Dickies Arena",
                "city": "Fort Worth",
                "country": "us"
              }
            }
          ]
        },
        {
          "_id": "614b6a2a143c37878b237b36",
          "slug": "7b36-rlcs-2021-22-spring-major",
          "name": "RLCS 2021-22 Spring Major",
          "startDate": "2022-06-28T23:00:00Z",
          "endDate": "2022-07-03T22:30:00Z",
          "region": "INT",
          "mode": 3,
          "prize": {
            "amount": 300000,
            "currency": "USD"
          },
          "tier": "S",
          "image": "https://griffon.octane.gg/events/rlcs-2021-22.png",
          "groups": [
            "rlcs",
            "rlcs2122",
            "rlcs2122spring"
          ],
          "stages": [
            {
              "_id": 0,
              "name": "Main Event",
              "region": "INT",
              "startDate": "2022-06-28T23:00:00Z",
              "endDate": "2022-07-03T22:30:00Z",
              "prize": {
                "amount": 300000,
                "currency": "USD"
              },
              "liquipedia": "https://liquipedia.net/rocketleague/Rocket_League_Championship_Series/2021-22/Spring",
              "lan": true,
              "location": {
                "venue": "Copper Box Arena",
                "city": "London",
                "country": "en"
              }
            }
          ]
        },
        {
          "_id": "614b784c143c37878b237b3d",
          "slug": "7b3d-rlcs-2021-22-spring-europe-regional-3",
          "name": "RLCS 2021-22 Spring Europe Regional 3",
          "startDate": "2022-05-28T23:00:00Z",
          "endDate": "2022-06-12T22:30:00Z",
          "region": "EU",
          "mode": 3,
          "prize": {
            "amount": 100000,
            "currency": "USD"
          },
          "tier": "A",
          "image": "https://griffon.octane.gg/events/rlcs-2021-22.png",
          "groups": [
            "rlcs",
            "rlcs2122",
            "rlcs2122spring"
          ],
          "stages": [
            {
              "_id": 0,
              "name": "Closed Qualifier",
              "region": "EU",
              "startDate": "2022-05-29T16:00:00Z",
              "endDate": "2022-05-29T22:30:00Z",
              "liquipedia": "https://liquipedia.net/rocketleague/Rocket_League_Championship_Series/2021-22/Spring/Europe/3/Closed_Qualifier",
              "qualifier": true
            },
            {
              "_id": 1,
              "name": "Main Event",
              "region": "EU",
              "startDate": "2022-06-10T15:00:00Z",
              "endDate": "2022-06-12T22:30:00Z",
              "prize": {
                "amount": 100000,
                "currency": "USD"
              },
              "liquipedia": "https://liquipedia.net/rocketleague/Rocket_League_Championship_Series/2021-22/Spring/Europe/3"
            }
          ]
        },
        {
          "_id": "614b7b37143c37878b237b42",
          "slug": "7b42-rlcs-2021-22-spring-south-america-regional-3",
          "name": "RLCS 2021-22 Spring South America Regional 3",
          "startDate": "2022-05-28T23:00:00Z",
          "endDate": "2022-06-05T22:30:00Z",
          "region": "SAM",
          "mode": 3,
          "prize": {
            "amount": 30000,
            "currency": "USD"
          },
          "tier": "A",
          "image": "https://griffon.octane.gg/events/rlcs-2021-22.png",
          "groups": [
            "rlcs",
            "rlcs2122",
            "rlcs2122spring"
          ],
          "stages": [
            {
              "_id": 0,
              "name": "Closed Qualifier",
              "region": "SAM",
              "startDate": "2022-05-29T19:00:00Z",
              "endDate": "2022-05-29T22:30:00Z",
              "liquipedia": "https://liquipedia.net/rocketleague/Rocket_League_Championship_Series/2021-22/Spring/South_America/3/Closed_Qualifier",
              "qualifier": true
            },
            {
              "_id": 1,
              "name": "Main Event",
              "region": "SAM",
              "startDate": "2022-06-03T19:00:00Z",
              "endDate": "2022-06-05T22:30:00Z",
              "prize": {
                "amount": 30000,
                "currency": "USD"
              },
              "liquipedia": "https://liquipedia.net/rocketleague/Rocket_League_Championship_Series/2021-22/Spring/South_America/3"
            }
          ]
        },
        {
          "_id": "614b7dae143c37878b237b44",
          "slug": "7b44-rlcs-2021-22-spring-asia-pacific-north-regional-3",
          "name": "RLCS 2021-22 Spring Asia-Pacific North Regional 3",
          "startDate": "2022-05-28T23:00:00Z",
          "endDate": "2022-06-05T22:30:00Z",
          "region": "ASIA",
          "mode": 3,
          "prize": {
            "amount": 15000,
            "currency": "USD"
          },
          "tier": "A",
          "image": "https://griffon.octane.gg/events/rlcs-2021-22.png",
          "groups": [
            "rlcs",
            "rlcs2122",
            "rlcs2122spring"
          ],
          "stages": [
            {
              "_id": 0,
              "name": "Closed Qualifier",
              "region": "ASIA",
              "startDate": "2022-05-29T10:00:00Z",
              "endDate": "2022-05-29T22:30:00Z",
              "liquipedia": "https://liquipedia.net/rocketleague/Rocket_League_Championship_Series/2021-22/Spring/Asia-Pacific_North/3/Closed_Qualifier",
              "qualifier": true
            },
            {
              "_id": 1,
              "name": "Main Event",
              "region": "ASIA",
              "startDate": "2022-06-03T10:00:00Z",
              "endDate": "2022-06-05T22:30:00Z",
              "prize": {
                "amount": 15000,
                "currency": "USD"
              },
              "liquipedia": "https://liquipedia.net/rocketleague/Rocket_League_Championship_Series/2021-22/Spring/Asia-Pacific_North/3"
            }
          ]
        },
        {
          "_id": "614b7f89f8090ec745286448",
          "slug": "6448-rlcs-2021-22-spring-sub-saharan-africa-regional-3",
          "name": "RLCS 2021-22 Spring Sub-Saharan Africa Regional 3",
          "startDate": "2022-05-28T23:00:00Z",
          "endDate": "2022-06-05T22:30:00Z",
          "region": "AF",
          "mode": 3,
          "prize": {
            "amount": 15000,
            "currency": "USD"
          },
          "tier": "A",
          "image": "https://griffon.octane.gg/events/rlcs-2021-22.png",
          "groups": [
            "rlcs",
            "rlcs2122",
            "rlcs2122spring"
          ],
          "stages": [
            {
              "_id": 0,
              "name": "Closed Qualifier",
              "region": "AF",
              "startDate": "2022-05-29T15:00:00Z",
              "endDate": "2022-05-29T22:30:00Z",
              "liquipedia": "https://liquipedia.net/rocketleague/Rocket_League_Championship_Series/2021-22/Spring/Sub-Saharan_Africa/3/Closed_Qualifier",
              "qualifier": true
            },
            {
              "_id": 1,
              "name": "Main Event",
              "region": "AF",
              "startDate": "2022-06-03T15:00:00Z",
              "endDate": "2022-06-05T22:30:00Z",
              "prize": {
                "amount": 15000,
                "currency": "USD"
              },
              "liquipedia": "https://liquipedia.net/rocketleague/Rocket_League_Championship_Series/2021-22/Spring/Sub-Saharan_Africa/3"
            }
          ]
        }
      ],
      "page": 1,
      "perPage": 100,
      "pageSize": 6
    }
""".trimIndent()

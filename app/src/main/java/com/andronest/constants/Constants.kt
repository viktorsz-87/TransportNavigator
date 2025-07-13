package com.andronest.constants

object Constants{

    object API_KEYS{
        const val RESROBOT_API_KEY = "caf31051-a6bb-4561-8cc6-125fea1ab3e8"
    }

    object Retrofit{
        const val BASE_URL_NEARBY_STOPS = "https://api.resrobot.se/"
    }

    object TrafikLab{

        val productTypes = mapOf<String,String>(
            "128" to "Bus",
            "512" to "Taxi",
            "2" to "High speed train",
            "32" to "Metro",
            "64" to "Tram",
            "16" to "Local train",
        )
    }
}

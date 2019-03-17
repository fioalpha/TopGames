package com.poc.fioalpha.a100topgames.data.model

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test

class TopGamesTransform {

    @Test
    fun `response transform to domain`() {
        val getTopGamesResponse: TopGamesResponse = getTopGameResponse()
        val value = getTopGamesResponse.transformToDomain()
        Assert.assertEquals(value.count(), 10)
        val game = value[0]
        Assert.assertEquals(game.countChannel, 15031)
        Assert.assertEquals(game.countViews, 185032)
        Assert.assertEquals(game.name, "Fortnite")
        Assert.assertEquals(game.image.count(), 4)
        Assert.assertEquals(game.image["small"], "https://static-cdn.jtvnw.net/ttv-boxart/Fortnite-52x72.jpg")
    }


    private fun getText(path: String): String {
        return javaClass.classLoader.getResource(path).readText()
    }

    private fun convertTextToObject(text: String): TopGamesResponse {
        return Gson().fromJson(text)
    }

    private fun getTopGameResponse(): TopGamesResponse {
        return convertTextToObject(getText("top_games_response.json"))
    }
}

inline fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, object: TypeToken<T>() {}.type)

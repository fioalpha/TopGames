package com.poc.fioalpha.a100topgames.presentation.model

import com.poc.fioalpha.a100topgames.domain.model.GamesDomain
import java.io.Serializable


data class GameViewModel (
    val name: String,
    val counterChannel: String,
    val counterViewer: String,
    val image: String
): Serializable

fun GamesDomain.transformToViewModel(): GameViewModel = GameViewModel(
    name = name,
    counterChannel = countChannel.toString(),
    counterViewer = countViews.toString(),
    image = image["large"]?: ""
)

fun transformToGamesDomainToViewModel(data: List<GamesDomain>): List<GameViewModel> {
    return data.map { it.transformToViewModel() }
}
package com.example.trivial.model

import kotlinx.serialization.Serializable

@Serializable
data class LotrCharacters(
    val data: List<Characters>,
) {
    @Serializable
    data class Characters(
        val name: String,
        val hair_color: String,
        val eye_color: String,
        val weapons: List<String>,
    )
}
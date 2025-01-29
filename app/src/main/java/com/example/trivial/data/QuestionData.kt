package com.example.trivial.ui.data

import com.example.trivial.model.Question

object QuestionData {
    val questions = listOf(
        Question(
            "¿Cuál es la capital de Francia?",
            listOf("Madrid", "Roma", "Berlín", "París"),
            "París"
        ),
        Question(
            "¿Quién pintó la Mona Lisa?",
            listOf("Van Gogh", "Picasso", "Rembrandt", "Da Vinci"),
            "Da Vinci"
        ),
        Question(
            "¿Qué planeta es conocido como el planeta rojo?",
            listOf("Venus", "Júpiter", "Saturno", "Marte"),
            "Marte"
        ),
        Question(
            "¿Cuántos continentes hay en el mundo?",
            listOf("5", "7", "9", "6"),
            "7"
        ),
        Question(
            "¿Cuál es el océano más grande?",
            listOf("Atlántico", "Índico", "Ártico", "Pacífico"),
            "Pacífico"
        ),
        Question(
            "¿En qué año llegó el hombre a la luna?",
            listOf("1960", "1970", "1980", "1969"),
            "1969"
        ),
        Question(
            "¿Quién escribió 'Cien años de soledad'?",
            listOf(
                "Pablo Neruda",
                "Gabriel García Márquez",
                "Mario Vargas Llosa",
                "Carlos Fuentes"
            ),
            "Gabriel García Márquez"
        ),
        Question(
            "¿Cuál es el animal terrestre más grande?",
            listOf("Rinoceronte", "Jirafa", "Hipopótamo", "Elefante"),
            "Elefante"
        ),
        Question(
            "¿Qué instrumento musical tiene 88 teclas?",
            listOf("Guitarra", "Violín", "Saxofón", "Piano"),
            "Piano"
        ),
        Question(
            "¿En qué continente se encuentra Egipto?",
            listOf("Asia", "Europa", "América", "África"),
            "África"
        )
    )
}


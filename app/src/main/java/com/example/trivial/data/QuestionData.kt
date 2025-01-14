package com.example.trivial.ui.data

import com.example.trivial.model.Question

object QuestionData {
    val questions = listOf(
        Question(
            text = "¿Cuál es la capital de Francia?",
            options = listOf("Madrid", "Roma", "Berlín", "París"),
            correctAnswer = "París"
        ),
        Question(
            text = "¿Quién pintó la Mona Lisa?",
            options = listOf("Van Gogh", "Picasso", "Rembrandt", "Da Vinci"),
            correctAnswer = "Da Vinci"
        ),
        Question(
            text = "¿Qué planeta es conocido como el planeta rojo?",
            options = listOf("Venus", "Júpiter", "Saturno", "Marte"),
            correctAnswer = "Marte"
        ),
        Question(
            text = "¿Cuántos continentes hay en el mundo?",
            options = listOf("5", "7", "9", "6"),
            correctAnswer = "7"
        ),
        Question(
            text = "¿Cuál es el océano más grande?",
            options = listOf("Atlántico", "Índico", "Ártico", "Pacífico"),
            correctAnswer = "Pacífico"
        ),
        Question(
            text = "¿En qué año llegó el hombre a la luna?",
            options = listOf("1960", "1970", "1980", "1969"),
            correctAnswer = "1969"
        ),
        Question(
            text = "¿Quién escribió 'Cien años de soledad'?",
            options = listOf(
                "Pablo Neruda",
                "Gabriel García Márquez",
                "Mario Vargas Llosa",
                "Carlos Fuentes"
            ),
            correctAnswer = "Gabriel García Márquez"
        ),
        Question(
            text = "¿Cuál es el animal terrestre más grande?",
            options = listOf("Rinoceronte", "Jirafa", "Hipopótamo", "Elefante"),
            correctAnswer = "Elefante"
        ),
        Question(
            text = "¿Qué instrumento musical tiene 88 teclas?",
            options = listOf("Guitarra", "Violín", "Saxofón", "Piano"),
            correctAnswer = "Piano"
        ),
        Question(
            text = "¿En qué continente se encuentra Egipto?",
            options = listOf("Asia", "Europa", "América", "África"),
            correctAnswer = "África"
        )
    )
}


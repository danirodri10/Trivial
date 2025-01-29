package com.example.trivial.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

//obtenemos la lista de preguntas de la API
@Serializable
data class QuestionApiResponse(
    val results: List<QuestionApi>,
) {
    @Serializable
    data class QuestionApi(
        @SerialName("question")
        val question: String,
        @SerialName("correct_answer")
        val correct_answer: String,
        @SerialName("incorrect_answers")
        val incorrect_answers: List<String>,
    ) {

        //transformamos la QuestionApi al formato de nuestra Question inicial
        fun toQuestion(): Question {
            val options = incorrect_answers.toMutableList()
            options.add(correct_answer)
            return Question(question, options.shuffled(), correct_answer)
        }
    }
}
package com.example.trivial.model

import com.example.trivial.ui.data.QuestionData.questions


data class Question(
    val rawQuestion: String,
    val rawOptions: List<String>,
    val rawCorrectAnswer: String, // Índice de la respuesta correcta
) {
    val question: String
        get() = rawQuestion.decodeHtml()

    val options: List<String>
        get() = rawOptions.map { it.decodeHtml() }

    val correctAnswer: String
        get() = rawCorrectAnswer.decodeHtml()

    fun validateAnswer(answer: String): Boolean {
        return answer == correctAnswer
    }
}

private fun String.decodeHtml(): String {
    return android.text.Html.fromHtml(this, android.text.Html.FROM_HTML_MODE_LEGACY).toString()
}

fun getQuestions(numberOfQuestions: Int): List<Question> {
    return questions.shuffled().take(numberOfQuestions)
}


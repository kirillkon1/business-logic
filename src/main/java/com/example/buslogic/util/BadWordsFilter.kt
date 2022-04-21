package com.example.buslogic.util

import org.springframework.core.io.ClassPathResource

class BadWordsFilter {

    private val forbiddenWords =
        try {
            ClassPathResource("ban_words.txt").file.readLines().toSet()
        } catch (e: Exception) {
            emptySet()
        }

    /**
     * if fun check returns false - the message has ban words
     */
    fun check(title: String, content: String = ""): Boolean =
        listOf(title, content)
            .flatMap { it.split(" ") }
            .toSet()
            .intersect(forbiddenWords).isEmpty()
}
package ru.smak.net

data class Request(
    val operation: String,
    val data: Any?
)
package ru.smak.net.entities

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object ChatUsers : IntIdTable() {
    val login = varchar("login", 200);

    val password = varchar("password", 200);
}
class ChatUser(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<ChatUser>(ChatUsers)

    var login by ChatUsers.login

    var password by ChatUsers.password

    override fun toString(): String = "login: ${login}| password: $password";
}
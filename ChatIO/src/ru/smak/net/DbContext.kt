package ru.smak.net

import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import ru.smak.net.entities.ChatUsers

object DbContext {
    private var _connection : Database? = null

    val Connection : Database?
        get() {
            if (_connection == null) {
                runBlocking {
                    if (_connection == null) {
                        _connection = Database.connect("jdbc:pgsql://localhost:5432/postgres", driver = "com.impossibl.postgres.jdbc.PGDriver",
                            user = "postgres", password = "root")
                    }
                }
            }

            return _connection;
        }

    fun create() {
        transaction {
            addLogger(StdOutSqlLogger)

            SchemaUtils.create(ChatUsers)
        }
    }
}
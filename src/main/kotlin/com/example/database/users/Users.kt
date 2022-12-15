package com.example.database.users

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object Users: IntIdTable() {
    private val login = varchar("userLogin", 50)
    private val password = varchar("password", 50)
    private val isAdmin = bool("isAdmin")

    fun insert(user: UserDTO) {
        transaction {
            Users.insert {
                it[login] = user.login
                it[password] = user.password
                it[isAdmin] = user.isAdmin
            }
        }
    }

    fun getUser(login: String): UserDTO? {
        val user = transaction { Users.select { Users.login.eq(login)}.singleOrNull() } ?: return null

        return UserDTO(
            id = user[id].value,
            login = user[Users.login],
            password = user[Users.password],
            isAdmin = user[Users.isAdmin]
        )
    }

    fun getUser(id: Int): UserDTO? {
        val user = transaction { Users.select { Users.id.eq(id)}.singleOrNull() } ?: return null

        return UserDTO(
            id = user[Users.id].value,
            login = user[Users.login],
            password = user[Users.password],
            isAdmin = user[Users.isAdmin]
        )
    }
}
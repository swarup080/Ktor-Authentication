package com.example.service

import com.example.database.DatabaseFactory
import com.example.database.UserTable
import com.example.model.User
import com.example.security.hash
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.statements.InsertStatement

class UserServiceImplementation: UserService {

    override suspend fun registerUser(params: CreateUserParams): User? {
        var statement: InsertStatement<Number>? = null
        DatabaseFactory.dbQuery {
            statement = UserTable.insert {
                it[email] = params.email
                it[password] = hash(params.password)
                it[fullName] = params.fullName
                it[avatar] = params.avatar
            }
        }
        return rowToUser(statement?.resultedValues?.get(0))
    }

    override suspend fun findUserByEmail(email: String): User? {
        val user = DatabaseFactory.dbQuery {
            UserTable.select { UserTable.email.eq(email) }.map { rowToUser(it) }.singleOrNull()
        }
        return user
    }

    private fun rowToUser(row: ResultRow?): User?{
        return if (row == null){
            return null
        }else{
            User(
                id = row[UserTable.id],
                fullName = row[UserTable.fullName],
                avatar = row[UserTable.avatar],
                email = row[UserTable.email],
                createdAt = row[UserTable.createdAt].toString()
            )
        }
    }
}
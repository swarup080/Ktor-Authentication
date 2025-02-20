package com.example.database

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object UserTable: Table("users") {

    var id = integer("id").autoIncrement()
    var fullName = varchar("full_name",256)
    val avatar = varchar("avatar",256)
    val email = varchar("email",256)
    val password = text("password")
    val createdAt = datetime("created_at").clientDefault { LocalDateTime.now() }

    override val primaryKey = PrimaryKey(id)
}
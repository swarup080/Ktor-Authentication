package com.example.repository

import com.example.model.User
import com.example.security.JwtConfig
import com.example.service.CreateUserParams
import com.example.service.UserService
import com.example.utils.BaseResponse

class UserRepositoryImplementation(private val userService: UserService) : UserRepository {

    override suspend fun registerUser(params: CreateUserParams): BaseResponse<Any> {
        return if (isEmailExist(params.email)) {
            BaseResponse.ErrorResponse(false,message = "Email already registered.",data = null)
        } else {
            val user = userService.registerUser(params)
            if (user != null) {
                val token = JwtConfig.instance.createAccessToken(user.id)
                user.authToken = token
                BaseResponse.SuccessResponse(true, message = "Registration Successfully", data = user)
            } else {
                BaseResponse.ErrorResponse(false, message = "Something went wrong",data = null)
            }
        }
    }

    override suspend fun loginUser(email: String, password: String): BaseResponse<Any> {
        TODO("Not yet implemented")
    }

    private suspend fun isEmailExist(email: String): Boolean {
        return userService.findUserByEmail(email) != null
    }
}
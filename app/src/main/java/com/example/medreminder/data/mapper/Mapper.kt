package com.example.medreminder.data.mapper

import com.example.medreminder.data.local.entity.UserEntity
import com.example.medreminder.domain.model.User


fun UserEntity.toDomain(): User{
    return User(
        id = this.id,
        userName=this.userName,
        passWord=this.passWord,
        isLoggedIn = this.isLoggedIn
    )

}

fun User.toEntity(): UserEntity{
    return UserEntity(
        id = this.id,
        userName=this.userName,
        passWord=this.passWord,
        isLoggedIn = this.isLoggedIn
    )
}
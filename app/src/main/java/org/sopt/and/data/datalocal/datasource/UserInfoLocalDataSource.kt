package org.sopt.and.data.datalocal.datasource

interface UserInfoLocalDataSource {
    var accessToken: String
    var userName: String
    var hobby: String
    fun clear()
}
package org.sopt.and.data.datalocal.datasourceimpl

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import org.sopt.and.data.datalocal.datasource.UserInfoLocalDataSource
import javax.inject.Inject

class UserInfoLocalDataSourceImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : UserInfoLocalDataSource {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

    override var accessToken: String
        get() = sharedPreferences.getString(ACCESSTOKEN, INITIAL_VALUE).toString()
        set(value) = sharedPreferences.edit { putString(ACCESSTOKEN, value)}

    override var userName: String
        get() = sharedPreferences.getString(USERNAME, INITIAL_VALUE).toString()
        set(value) = sharedPreferences.edit { putString(USERNAME, value) }

    override var hobby: String
        get() = sharedPreferences.getString(HOBBY, INITIAL_VALUE).toString()
        set(value) = sharedPreferences.edit { putString(HOBBY, value) }

    override fun clear() = sharedPreferences.edit { clear() }

    companion object {
        const val PREFERENCES_NAME = "user_preferences"
        const val ACCESSTOKEN = "accesstoken"
        const val USERNAME = "userName"
        const val HOBBY = "hobby"
        const val INITIAL_VALUE = ""
    }
}
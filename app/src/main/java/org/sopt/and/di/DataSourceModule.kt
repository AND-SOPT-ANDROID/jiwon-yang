package org.sopt.and.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.and.data.datalocal.datasource.UserInfoLocalDataSource
import org.sopt.and.data.datalocal.datasourceimpl.UserInfoLocalDataSourceImpl
import org.sopt.and.data.dataremote.datasource.UserInfoRemoteDataSource
import org.sopt.and.data.dataremote.datasourceimpl.UserInfoRemoteDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun bindsUserRemoteDataSource(userInfoRemoteDataSourceImpl: UserInfoRemoteDataSourceImpl): UserInfoRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindsUserLocalDataSource(userInfoLocalDataSourceImpl: UserInfoLocalDataSourceImpl): UserInfoLocalDataSource
}
package ru.vitalysizov.moviedb.di.components

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import ru.vitalysizov.moviedb.App
import ru.vitalysizov.moviedb.di.modules.*
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        NetworkModule::class,
        RepositoriesModule::class,
        ViewModelFactoryModule::class,
        ActivityBuilder::class,
        StorageModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: Application): Builder

        fun netWorkModule(networkModule: NetworkModule): Builder

        fun repositoryModule(repositoryModule: RepositoriesModule): Builder

        fun storageModule(storageModule: StorageModule): Builder

        fun build(): AppComponent
    }

    override fun inject(app: App)

}
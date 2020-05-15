package ru.vitalysizov.moviedb.di.components

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import ru.vitalysizov.moviedb.App
import ru.vitalysizov.moviedb.di.modules.ActivityBuilder
import ru.vitalysizov.moviedb.di.modules.NetworkModule
import ru.vitalysizov.moviedb.di.modules.RepositoriesModule
import ru.vitalysizov.moviedb.di.modules.ViewModelFactoryModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        NetworkModule::class,
        RepositoriesModule::class,
        ViewModelFactoryModule::class,
        ActivityBuilder::class
    ]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: Application): Builder

        fun netWorkModule(networkModule: NetworkModule): Builder

        fun repositoryModule(repositoryModule: RepositoriesModule): Builder

        fun build(): AppComponent
    }

    override fun inject(app: App)

}
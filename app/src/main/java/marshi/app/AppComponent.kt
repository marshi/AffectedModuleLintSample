package marshi.app

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule

@Component(
  modules = [
    AndroidInjectionModule::class,
    AndroidSupportInjectionModule::class,
    FragmentDaggerModule::class
  ]
)
interface AppComponent {

//  @Component.Builder
//  interface Builder {
//    @BindsInstance
//    fun app(app: App): Builder
//
//    fun build(): AppComponent
//  }

  @Component.Factory
  interface Factory {
    fun create(@BindsInstance app: App): AppComponent
  }

  fun inject(app: App)
}

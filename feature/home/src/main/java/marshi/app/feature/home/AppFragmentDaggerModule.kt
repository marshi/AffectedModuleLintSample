package marshi.app.feature.home

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface AppFragmentDaggerModule {

  @ContributesAndroidInjector(modules = [])
  fun contributeAppFragment(): AppFragment
}


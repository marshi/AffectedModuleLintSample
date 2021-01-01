package marshi.app

import dagger.Module
import marshi.app.feature.home.AppFragmentDaggerModule

@Module(
    includes = [AppFragmentDaggerModule::class]
)
interface FragmentDaggerModule

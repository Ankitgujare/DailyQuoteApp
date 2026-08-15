package gaur.himanshu.workmanageryt

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.WorkManager
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class BaseApplication: Application() {


    /**
     *To use Dagger- Hilt in our Worker classes
     * we need to dissable all the Default things that we are getting from
     * Workmanager Library
     */

    @Inject
    lateinit var hiltWorkerFactory: HiltWorkerFactory

    override fun onCreate() {
        super.onCreate()
        WorkManager.initialize(
            this,
            Configuration.Builder()
                .setWorkerFactory(hiltWorkerFactory)
                .build()
        )
    }
}
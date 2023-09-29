package br.com.alexfelipe.testappkmm

import br.com.alexfelipe.testappkmm.cache.Database
import br.com.alexfelipe.testappkmm.cache.DatabaseDriverFactory
import br.com.alexfelipe.testappkmm.entities.RocketLaunch
import br.com.alexfelipe.testappkmm.network.SpaceXApi

class SpaceXSDK(
    databaseDriverFactory: DatabaseDriverFactory
) {
    private val database = Database(databaseDriverFactory)
    private val api = SpaceXApi()

    @Throws(Exception::class)
    suspend fun getLaunches(forceReload: Boolean): List<RocketLaunch> {
        val cachedLaunches = database.getAllLaunches()
        return if (cachedLaunches.isNotEmpty() && !forceReload) {
            cachedLaunches
        } else {
            api.getAllLaunches().also {
                database.clearDatabase()
                database.createLaunches(it)
            }
        }
    }
}

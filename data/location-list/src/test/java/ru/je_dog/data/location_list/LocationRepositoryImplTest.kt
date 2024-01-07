package ru.je_dog.data.location_list

import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import ru.je_dog.core.domain.model.GeoPointDomain
import ru.je_dog.data.location_list.storage.LocationStorageDataSource

class LocationRepositoryImplTest {

    private val locationStorageDataSource = LocationStorageDataSource.Mock()
    private val locationRepositoryImpl = LocationRepositoryImpl(locationStorageDataSource)

    @Before
    fun beforeEach(){
        locationStorageDataSource.isSuccess = true
    }

    @Test
    fun `success get all location`() = runBlocking {
        //call
        val actual = locationRepositoryImpl.getAllLocation().last()
        //assert
        assertTrue(actual.isNotEmpty())
    }

    @Test
    fun `failure get all location`() = runBlocking {
        //call
        locationStorageDataSource.isSuccess = false
        val actual = locationRepositoryImpl.getAllLocation().last()
        //assert
        assertTrue(actual.isEmpty())
    }

    @Test
    fun `success add location`() = runBlocking {
        //call
        val geoPoint = GeoPointDomain.mock()
        val actual = locationRepositoryImpl.addLocation(geoPoint)
        //assert
        assertTrue(actual)
    }

    @Test
    fun `failure add location`() = runBlocking {
        //call
        val geoPoint = GeoPointDomain.mock()
        locationStorageDataSource.isSuccess = false
        val actual = locationRepositoryImpl.addLocation(geoPoint)
        //assert
        assertFalse(actual)
    }

    @Test
    fun `success update location`() = runBlocking {
        //call
        val geoPoint = GeoPointDomain.mock()
        val actual = locationRepositoryImpl.updateLocation(geoPoint)
        //assert
        assertTrue(actual)
    }

    @Test
    fun `failure update location`() = runBlocking {
        //call
        val geoPoint = GeoPointDomain.mock()
        locationStorageDataSource.isSuccess = false
        val actual = locationRepositoryImpl.updateLocation(geoPoint)
        //assert
        assertFalse(actual)
    }

    @Test
    fun `success delete location`() = runBlocking {
        //call
        val geoPoint = GeoPointDomain.mock()
        val actual = locationRepositoryImpl.deleteLocation(geoPoint)
        //assert
        assertTrue(actual)
    }

    @Test
    fun `failure delete location`() = runBlocking {
        //call
        val geoPoint = GeoPointDomain.mock()
        locationStorageDataSource.isSuccess = false
        val actual = locationRepositoryImpl.deleteLocation(geoPoint)
        //assert
        assertFalse(actual)
    }

    @Test
    fun `success delete all location`() = runBlocking {
        //call
        val actual = locationRepositoryImpl.deleteAllLocation()
        //assert
        assertTrue(actual)
    }

    @Test
    fun `failure delete all location`() = runBlocking {
        //call
        locationStorageDataSource.isSuccess = false
        val actual = locationRepositoryImpl.deleteAllLocation()
        //assert
        assertFalse(actual)
    }

}
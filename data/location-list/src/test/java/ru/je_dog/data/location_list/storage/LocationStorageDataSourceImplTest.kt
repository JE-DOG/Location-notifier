package ru.je_dog.data.location_list.storage

import junit.framework.TestCase
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import ru.je_dog.core.domain.model.GeoPointDomain
import ru.je_dog.data.location_list.storage.room.LocationDao

class LocationStorageDataSourceImplTest {

    private val locationDao = LocationDao.Mock()
    private val locationStorageDataSource = LocationStorageDataSourceImpl(locationDao)

    @Before
    fun beforeEach(){
        locationDao.isSuccess = true
    }

    @Test
    fun `success get all location`() = runBlocking {
        //call
        val actual = locationStorageDataSource.getAllLocation().last()
        //assert
        TestCase.assertTrue(actual.isNotEmpty())
    }

    @Test
    fun `failure get all location`() = runBlocking {
        //call
        locationDao.isSuccess = false
        val actual = locationStorageDataSource.getAllLocation().last()
        //assert
        TestCase.assertTrue(actual.isEmpty())
    }

    @Test
    fun `success add location`() = runBlocking {
        //call
        val geoPoint = GeoPointDomain.mock()
        val actual = locationStorageDataSource.addLocation(geoPoint)
        //assert
        TestCase.assertTrue(actual)
    }

    @Test
    fun `failure add location`() = runBlocking {
        //call
        val geoPoint = GeoPointDomain.mock()
        locationDao.isSuccess = false
        val actual = locationStorageDataSource.addLocation(geoPoint)
        //assert
        TestCase.assertFalse(actual)
    }

    @Test
    fun `success update location`() = runBlocking {
        //call
        val geoPoint = GeoPointDomain.mock()
        val actual = locationStorageDataSource.updateLocation(geoPoint)
        //assert
        TestCase.assertTrue(actual)
    }

    @Test
    fun `failure update location`() = runBlocking {
        //call
        val geoPoint = GeoPointDomain.mock()
        locationDao.isSuccess = false
        val actual = locationStorageDataSource.updateLocation(geoPoint)
        //assert
        TestCase.assertFalse(actual)
    }

    @Test
    fun `success delete location`() = runBlocking {
        //call
        val geoPoint = GeoPointDomain.mock()
        val actual = locationStorageDataSource.deleteLocation(geoPoint)
        //assert
        TestCase.assertTrue(actual)
    }

    @Test
    fun `failure delete location`() = runBlocking {
        //call
        val geoPoint = GeoPointDomain.mock()
        locationDao.isSuccess = false
        val actual = locationStorageDataSource.deleteLocation(geoPoint)
        //assert
        TestCase.assertFalse(actual)
    }

    @Test
    fun `success delete all location`() = runBlocking {
        //call
        val actual = locationStorageDataSource.deleteAllLocation()
        //assert
        TestCase.assertTrue(actual)
    }

    @Test
    fun `failure delete all location`() = runBlocking {
        //call
        locationDao.isSuccess = false
        val actual = locationStorageDataSource.deleteAllLocation()
        //assert
        TestCase.assertFalse(actual)
    }

}
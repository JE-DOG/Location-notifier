package ru.je_dog.domain.location_list.use_case

import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import ru.je_dog.core.domain.model.GeoPointDomain
import ru.je_dog.domain.location_list.LocationRepository

class UpdateLocationUseCaseTest {

    private val locationRepository = LocationRepository.Mock()

    @Before
    fun beforeEach(){
        locationRepository.isSuccess = true
    }

    @Test
    fun `success use case call`(): Unit = runBlocking {
        //Ready
        val useCase = UpdateLocationUseCase(locationRepository)
        //Call
        val actual = useCase.execute(GeoPointDomain.mock())
        //Assert
        Assert.assertTrue(actual)
    }

    @Test
    fun `failure use case call`(): Unit = runBlocking {
        //Ready
        val useCase = UpdateLocationUseCase(locationRepository)
        //Call
        locationRepository.isSuccess = false
        val actual = useCase.execute(GeoPointDomain.mock())
        //Assert
        Assert.assertFalse(actual)
    }

}
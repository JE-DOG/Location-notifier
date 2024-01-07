package ru.je_dog.domain.location_list.use_case

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import ru.je_dog.core.domain.model.GeoPointDomain
import ru.je_dog.domain.location_list.LocationRepository

class GetAllLocationUseCaseTest {

    private val locationRepository = LocationRepository.Mock()

    @Before
    fun beforeEach(){
        locationRepository.isSuccess = true
    }

    @Test
    fun `success use case call`(): Unit = runBlocking {
        //Ready
        val useCase = GetAllLocationUseCase(locationRepository)
        //Call
        val actual = useCase.execute().last()
        //Assert
        Assert.assertTrue(actual.isNotEmpty())
    }

    @Test
    fun `failure use case call`(): Unit = runBlocking {
        //Ready
        val useCase = GetAllLocationUseCase(locationRepository)
        //Call
        locationRepository.isSuccess = false
        val actual = useCase.execute().last()
        //Assert
        Assert.assertTrue(actual.isEmpty())
    }

}
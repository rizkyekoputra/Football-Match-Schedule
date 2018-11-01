package com.example.rizkyekoputra.footballmatchschedule.rest

import org.junit.Test
import org.mockito.Mockito

class ApiRepositoryTest {

    @Test
    fun doRequest() {
        val apiRepository = Mockito.mock(ApiRepository::class.java)
        val url = "https://www.thesportsdb.com/api/v1/json/1/lookupevent.php?id=441613"
        apiRepository.doRequest(url)
        Mockito.verify(apiRepository).doRequest(url)
    }
}
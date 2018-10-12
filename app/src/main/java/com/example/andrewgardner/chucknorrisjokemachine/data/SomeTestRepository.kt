/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zozo.pb.repo

import io.reactivex.Flowable
import io.reactivex.Single

class SomeTestRepository private constructor(
    /*private val testDao: TestDao*/
) {

    fun createTestObject(testID: String) {
        // runOnIoThread {
        // val testObject = TestObject()
        // testDao.insertTestObject(testObject)
        // }
    }

    // fun getTestObjectForObject(objectId: String) = testDao.getTestObjectForObject(objectId)

    // fun getTestObjectsForObjects() = testDao.getTestObjectsForObjects()

    public fun getMessage(): Single<String> {
        val stringMessage = "Hello I am a String"
        return Single.just(stringMessage)
    }

    //fun getCities(): Flowable<List<CityEntity>>

    //fun getWeather(cityName: String): Single<WeatherDetailsDTO>

    //fun addCity(cityName: String)

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: SomeTestRepository? = null

        fun getInstance(/*testDao: TestDao*/) =
                instance ?: synchronized(this) {
                    instance ?: SomeTestRepository(/*testDao*/).also { instance = it }
                }
    }
}

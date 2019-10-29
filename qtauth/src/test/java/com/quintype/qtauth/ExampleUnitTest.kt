package com.quintype.qtauth

import com.google.gson.Gson
import com.quintype.qtauth.models.MemberRequestModel
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
//        assertEquals(4, 2 + 2)

        val loginRequest = MemberRequestModel()
        loginRequest.email = "rakshith@quintype.com"

        println(Gson().toJson(loginRequest))
    }
}

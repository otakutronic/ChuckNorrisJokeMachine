package com.example.andrewgardner.chucknorrisjokemachine.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Eyehunt Team on 12/06/18.
 */
class UsersList {
    @SerializedName("items")
    var users: List<Users>? = null
}
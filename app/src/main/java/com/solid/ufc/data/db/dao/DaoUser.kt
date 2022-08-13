package com.solid.ufc.data.db.dao

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.solid.ufc.data.model.signup.User

class DaoUser {
    private lateinit var databaseReference: DatabaseReference

    init {
        val firebaseDB = FirebaseDatabase.getInstance()
        databaseReference = firebaseDB.getReference(User::class.java.name)
    }
}
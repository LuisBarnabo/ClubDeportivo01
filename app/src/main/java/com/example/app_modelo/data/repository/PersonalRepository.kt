package com.example.app_modelo.data.repository

import com.example.app_modelo.data.dao.PersonalDao
import com.example.app_modelo.data.model.Personal

class PersonalRepository(private val personalDao: PersonalDao) {

    fun insertarPersonal(personal: Personal): Long {
        return personalDao.insertarPersonal(personal)
    }
}
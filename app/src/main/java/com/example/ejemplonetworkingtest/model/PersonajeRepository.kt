package com.example.ejemplonetworkingtest.model

import com.example.ejemplonetworkingtest.app.App
import com.example.ejemplonetworkingtest.data.OperationCallback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class PersonajeRepository(private val personajeDataSource: PersonajeDataSource) {


    suspend fun fetchPersonajesCallback(callback: OperationCallback<PersonajeModel>) = withContext(Dispatchers.IO) {
        if (isEmpty())
            personajeDataSource.obtenerPersonajesCallback(callback)
        else {
            callback.onSuccess(App.getDataBase().personajeDao().getAll())
        }
    }

    suspend fun cancel()  = withContext(Dispatchers.IO) {
        personajeDataSource.cancel()
    }

    internal fun isEmpty() : Boolean {
        return App.getDataBase().personajeDao().count() == 0
    }
}
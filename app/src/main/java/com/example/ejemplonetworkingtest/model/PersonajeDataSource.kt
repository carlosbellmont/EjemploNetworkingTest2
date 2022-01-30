package com.example.ejemplonetworkingtest.model

import com.example.ejemplonetworkingtest.data.OperationCallback

interface PersonajeDataSource {

    suspend fun obtenerPersonajesCallback(callback: OperationCallback<PersonajeModel>)
    suspend fun cancel()
}
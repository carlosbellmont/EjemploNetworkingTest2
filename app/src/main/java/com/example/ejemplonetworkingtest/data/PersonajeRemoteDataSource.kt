package com.example.ejemplonetworkingtest.data

import com.example.ejemplonetworkingtest.app.App
import com.example.ejemplonetworkingtest.model.PersonajeDataSource
import com.example.ejemplonetworkingtest.model.PersonajeModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PersonajeRemoteDataSource(apiClient: ApiClient) : PersonajeDataSource {

    private var call: Call<List<PersonajeModel>>? = null
    private val service = apiClient.build()


    fun insertAll(list : List<PersonajeModel>) {
        App.getDataBase().personajeDao().insertAll(list)
    }

    override suspend fun obtenerPersonajesCallback(callback: OperationCallback<PersonajeModel>) {

        call = service.obtenerPersonajes()
        call?.enqueue(object : Callback<List<PersonajeModel>> {
            override fun onFailure(call: Call<List<PersonajeModel>>, t: Throwable) {
                CoroutineScope(Dispatchers.IO).launch {
                    callback.onError(t.message)
                }
            }

            override fun onResponse(call: Call<List<PersonajeModel>>, response: Response<List<PersonajeModel>>) {
                CoroutineScope(Dispatchers.IO).launch {
                    response.body()?.let {
                        if (response.isSuccessful) {
                            response.body()?.let { peronajesList ->
                                insertAll(peronajesList)
                            }

                            callback.onSuccess(it)
                        } else {
                            callback.onError("error")
                        }
                    }
                }
            }
        })
    }

    override suspend fun cancel() {
        call?.cancel()
    }
}
package com.abhay.taskapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.abhay.taskapp.R
import com.abhay.taskapp.adapter.UserAdapter
import com.abhay.taskapp.models.User
import com.abhay.taskapp.service.Service
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_view.setHasFixedSize(true)
        recycler_view.layoutManager = LinearLayoutManager(this)


        val service: Service = Retrofit.Builder()
            .baseUrl("http://demo8716682.mockable.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Service::class.java)

        progress_bar.visibility = View.VISIBLE



        CoroutineScope(Dispatchers.IO).launch {
            service?.let {
                var call = service.getUsers()

                call?.let {
                    call.enqueue(object : Callback<List<User>> {
                        override fun onFailure(call: Call<List<User>>, t: Throwable) {
                            Toast.makeText(this@MainActivity, "Request Failed", Toast.LENGTH_LONG)
                                .show()
                            progress_bar.visibility = View.GONE
                        }

                        override fun onResponse(
                            call: Call<List<User>>,
                            response: Response<List<User>>
                        ) {

                            if (response.isSuccessful) {
                                var users = response.body()
                                var adapter = UserAdapter(users!!)
                                recycler_view.adapter = adapter
                            } else {
                                Toast.makeText(
                                    this@MainActivity,
                                    "Request Failed",
                                    Toast.LENGTH_LONG
                                )
                                    .show()
                            }

                            progress_bar.visibility = View.GONE

                        }

                    })
                }

            }
        }


    }
}

package com.example.project.viewmodals

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.project.api.RetrofitHelper
import com.example.project.modals.LiveScoreResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LiveScoreViewModel {
    private val liveScoreResponse = MutableLiveData< LiveScoreResponse >()
    var liveScore : LiveData<LiveScoreResponse> = liveScoreResponse
    fun getLiveScores(){
        RetrofitHelper.retrofitService.getLiveScore("1").enqueue(
            object : Callback<LiveScoreResponse> {
                override fun onResponse(
                    call: Call<LiveScoreResponse>,
                    response: Response<LiveScoreResponse>
                ) {
                    if(response.isSuccessful){
                        Log.d("Retrofit livescore", response.body().toString())
                        liveScoreResponse.value = response.body()
                        liveScore = liveScoreResponse
                    }
                }

                override fun onFailure(call: Call<LiveScoreResponse>, t: Throwable) {
                    Log.e("Retrofit", "API call failed: ${t.message}")

                }
            }
        )
    }
}
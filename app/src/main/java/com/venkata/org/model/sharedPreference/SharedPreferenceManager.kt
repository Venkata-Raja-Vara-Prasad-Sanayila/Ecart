package com.venkata.org.model.sharedPreference

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.google.gson.Gson
import com.venkata.org.model.data.login.User

object SharedPreferenceManager {

    private const val PREF_NAME = "ecart"
    const val KEY_IS_LOGGED_IN = "isLoggedIn"
    const val KEY_NAME = "nameUser"
    const val KEY_USER = "userInfo"

    private lateinit var sharedPreferences: SharedPreferences


    fun init(context: Context){

        val masterKey = MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build()

        sharedPreferences = EncryptedSharedPreferences.create(

            context, PREF_NAME, masterKey, EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

    }

    fun saveBoolean(key:String, value:Boolean){

        sharedPreferences.edit().putBoolean(key, value).apply()

    }

    fun getBoolean(key:String):Boolean{
       return sharedPreferences.getBoolean(key, false)
    }

    fun  saveString(key: String, value: String){
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun getString(key: String):String?{
        return  sharedPreferences.getString(key, null)
    }

    fun saveUser(key: String, user: User){
        val user = Gson().toJson(user)
        sharedPreferences.edit().putString(key, user).apply()
    }

    fun getUser(key: String): User?{
        val  userJason = sharedPreferences.getString(key, null)
        return Gson().fromJson(userJason, User::class.java)
    }

    fun clearPreference(){
        sharedPreferences.edit().clear().apply()
    }


}
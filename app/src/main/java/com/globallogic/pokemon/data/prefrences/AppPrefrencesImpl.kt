package com.globallogic.pokemon.data.prefrences

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AppPrefrencesImpl @Inject constructor(@ApplicationContext context: Context) :
    AppPrefrencesHelper {

    companion object {
        @Volatile
        private var mprefs: SharedPreferences? = null
        private val PREF_KEY_TOKEN: String = "PREF_KEY_ACCESS_TOKEN"
        fun getPrefrences(context: Context): SharedPreferences = mprefs ?: synchronized(this) {
            mprefs ?: buildPrefrences(
                context
            ).also { mprefs = it }
        }

        private fun buildPrefrences(context: Context): SharedPreferences {
            return context.getSharedPreferences(PREF_KEY_TOKEN, Context.MODE_PRIVATE)
        }
    }

    init {
        getPrefrences(context)
    }


    override fun setAceesToken(token: String) =
        mprefs?.edit()?.putString("token", token)?.apply()

    override fun getAccessToken(): String? = mprefs?.getString("token", null)

    override fun setRefreshToken(token: String) =
        mprefs?.edit()?.putString("refresh_token", token)?.apply()

    override fun getIsLogin(): Boolean? = mprefs?.getBoolean("isLogin", false)

    override fun setIsLogin(isLogin: Boolean) =
        mprefs?.edit()?.putBoolean("isLogin", isLogin)?.apply()

    override fun getInquiryId(): String? = mprefs?.getString("inquiry_id", "")

    override fun setInquiryId(inquiryId: String) =
        mprefs?.edit()?.putString("inquiry_id", inquiryId)?.apply()

    override fun getRefreshToken(): String? = mprefs?.getString("refresh_token", null)

    override fun clearAllThing() {
        mprefs?.edit()?.clear()?.apply()



    }



}
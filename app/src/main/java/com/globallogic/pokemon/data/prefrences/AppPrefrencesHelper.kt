package com.globallogic.pokemon.data.prefrences

interface AppPrefrencesHelper {
    fun setAceesToken(token:String): Unit?
    fun getAccessToken():String?
    fun getIsLogin(): Boolean?
    fun setIsLogin(isLogin: Boolean):Unit?
    fun clearAllThing()

}
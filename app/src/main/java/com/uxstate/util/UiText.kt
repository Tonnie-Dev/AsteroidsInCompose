package com.uxstate.util

import android.content.Context

sealed class UiText {

    data class DynamicString(val originalText:String):UiText()
    data class StringResource(val resId:Int):UiText()

    fun asString(context: Context):String{

        return when(this){

            is DynamicString -> originalText
            is StringResource -> context.getString(resId)
        }

    }
}


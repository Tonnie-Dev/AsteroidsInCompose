package com.uxstate.util

import android.content.Context

sealed class UiText {

    /*houses strings from String Resources and also Dynamic
  Strings e.g. from a remote API where we don't have the
   ability to read it from string resources. In this case
   we need to retrieve the string resources or show the
   string as it is (Dynamic) or as we
   get it from the API (Dynamic)*/
    data class DynamicString(val originalText:String):UiText()
    data class StringResource(val resId:Int):UiText()


    //helper function to unwrap UiText Class
    fun asString(context: Context):String{


        //this refers to UiText sealed class
        return when(this){

            is DynamicString -> originalText
            is StringResource -> context.getString(resId)
        }

    }
}


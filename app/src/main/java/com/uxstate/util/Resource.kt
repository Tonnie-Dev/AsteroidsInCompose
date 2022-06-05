package com.uxstate.util

//Resource is a generic class
sealed class Resource<T>(val data:T? = null, val message:String? = null){


    //3 subclasses which are also generic

    class Success<T>(data: T?):Resource<T>(data)

    /*We attach a nullable data to the Error case as we can
    * still return some data from the database cache*/
    class Error<T>(message: String, data: T? = null):Resource<T>(data, message)

    class Loading<T>(val isLoading:Boolean = false):Resource<T>(null)
}

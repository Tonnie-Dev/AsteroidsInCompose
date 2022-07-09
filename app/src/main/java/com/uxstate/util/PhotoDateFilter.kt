package com.uxstate.util

sealed class PhotoDateFilter{

    object TodayPhotos: PhotoDateFilter()
    object RecentPhotos: PhotoDateFilter()
    object AllPhotos: PhotoDateFilter()
}

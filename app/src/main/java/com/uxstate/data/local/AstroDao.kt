package com.uxstate.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface AstroDao {

@Insert(onConflict = OnConflictStrategy.REPLACE)
fun insertFavoriteAstroPhoto(photoEntity: AstroPhotoEntity)


}
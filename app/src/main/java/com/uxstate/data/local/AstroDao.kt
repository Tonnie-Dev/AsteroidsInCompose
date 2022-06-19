package com.uxstate.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.uxstate.domain.model.AstroPhoto

@Dao
interface AstroDao {

@Insert(onConflict = OnConflictStrategy.REPLACE)
fun insertFavoriteAstroPhoto(photoEntity: AstroPhotoEntity)

@Query("SELECT * FROM astrophotoentity ")
fun getFavoriteAstroPhotos():List<AstroPhotoEntity>

@Query("SELECT * FROM astrophotoentity WHERE date ==:id")
fun getPhotoById(id:String):AstroPhoto?



}
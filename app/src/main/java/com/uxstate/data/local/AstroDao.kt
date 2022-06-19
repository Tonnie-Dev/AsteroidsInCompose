package com.uxstate.data.local

import androidx.room.*
import com.uxstate.domain.model.AstroPhoto

@Dao
interface AstroDao {

@Insert(onConflict = OnConflictStrategy.REPLACE)
fun insertFavoriteAstroPhoto(photoEntity: AstroPhotoEntity)

@Query("SELECT * FROM astrophotoentity ")
fun getFavoriteAstroPhotos():List<AstroPhotoEntity>

@Query("SELECT * FROM astrophotoentity WHERE date =:id")
fun getPhotoById(id:String):AstroPhoto?


@Delete
fun deletePhoto(id:String)

}
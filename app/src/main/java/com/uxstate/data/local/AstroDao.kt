package com.uxstate.data.local

import androidx.room.*
import com.uxstate.domain.model.AstroPhoto
import kotlinx.coroutines.flow.Flow

@Dao
interface AstroDao {

@Insert(onConflict = OnConflictStrategy.REPLACE)
suspend fun insertFavoriteAstroPhoto(photoEntity: AstroPhotoEntity)

@Query("SELECT * FROM astrophotoentity ")
suspend fun getFavoriteAstroPhotos():List<AstroPhotoEntity>?

@Query("SELECT * FROM astrophotoentity WHERE date =:id")
suspend fun getFavoritePhotoById(id:String):AstroPhoto?

@Delete
suspend fun deleteFavoritePhoto(id:String)

}
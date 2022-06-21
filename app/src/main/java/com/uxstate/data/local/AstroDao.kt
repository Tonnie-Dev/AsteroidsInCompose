package com.uxstate.data.local

import androidx.room.*
import com.uxstate.domain.model.AstroPhoto
import kotlinx.coroutines.flow.Flow

@Dao
interface AstroDao {

@Insert(onConflict = OnConflictStrategy.REPLACE)
suspend fun insertFavoriteAstroPhoto(photoEntity: AstroPhotoEntity)

@Query("SELECT * FROM astrophotoentity ")
 fun getFavoriteAstroPhotos():Flow<List<AstroPhotoEntity>?>

@Query("SELECT * FROM astrophotoentity WHERE id =:id")
suspend fun getFavoritePhotoById(id:String):AstroPhotoEntity?

@Delete
suspend fun deleteFavoritePhoto(photo: AstroPhotoEntity)

}
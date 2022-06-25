package com.uxstate.data.local

import androidx.room.*
import com.uxstate.domain.model.AstroPhoto
import kotlinx.coroutines.flow.Flow

@Dao
interface AstroDao {


//AstroPhotoEntity


@Query("SELECT * FROM astrophotoentity ")
 fun getFavoriteAstroPhotos():Flow<List<AstroPhotoEntity>?>

@Query("SELECT * FROM astrophotoentity WHERE id =:id")
suspend fun getFavoritePhotoById(id:String):AstroPhotoEntity?

@Delete
suspend fun deleteFavoritePhoto(photo: AstroPhotoEntity)


@Query("SELECT EXISTS(SELECT 1 FROM astrophotoentity WHERE id=:id)")
suspend fun photoExists(id:String):Boolean

 //FavPhotoEntity
 @Insert(onConflict = OnConflictStrategy.REPLACE)
 suspend fun insertFavoritePhoto(photo: FavPhotoEntity)


}
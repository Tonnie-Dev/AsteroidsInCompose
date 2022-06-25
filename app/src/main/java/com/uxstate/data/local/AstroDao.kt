package com.uxstate.data.local

import androidx.room.*
import com.uxstate.domain.model.AstroPhoto
import kotlinx.coroutines.flow.Flow

@Dao
interface AstroDao {


//ASTROPHOTOENTITY
 @Insert(onConflict = OnConflictStrategy.REPLACE)
 suspend fun insertAstroFavPhotos(list: List<AstroPhotoEntity>)



@Query("DELETE FROM astrophotoentity")
suspend fun clearAstroPhotos()

@Query("SELECT * FROM astrophotoentity")
suspend fun fetchAstroPhotos():List<AstroPhotoEntity>






 //FAVPHOTOENTITY
 @Insert(onConflict = OnConflictStrategy.REPLACE)
 suspend fun insertFavoritePhoto(photo: FavPhotoEntity)

 @Query("SELECT * FROM favphotoentity WHERE id =:id")
 suspend fun getFavoritePhotoById(id:String):FavPhotoEntity?

 @Delete
 suspend fun deleteFavoritePhoto(photo: FavPhotoEntity)

 @Query("SELECT EXISTS(SELECT 1 FROM favphotoentity WHERE id=:id)")
 suspend fun isFavPhotoSavedCheck(id:String):Boolean

 @Query("SELECT * FROM astrophotoentity ")
 fun getFavPhotos():Flow<List<FavPhotoEntity>?>


}
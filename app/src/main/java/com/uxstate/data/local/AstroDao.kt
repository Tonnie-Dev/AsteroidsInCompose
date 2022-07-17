package com.uxstate.data.local

import androidx.room.*
import com.uxstate.domain.model.AstroPhoto
import kotlinx.coroutines.flow.Flow

@Dao
interface AstroDao {


    //ASTROPHOTOENTITY
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAstroPhotos(list: List<AstroPhotoEntity>)

    @Query("SELECT * FROM astrophotoentity")
    suspend fun fetchAstroPhotos():List<AstroPhotoEntity>

    @Query("SELECT * FROM astrophotoentity")
    fun getLiveAstroPhotos():Flow<List<AstroPhotoEntity>>

    @Query("UPDATE astrophotoentity SET isFavorite =:isFavorite WHERE id=:id")
    suspend fun updateIsFavoriteStatus(id: String,isFavorite:Boolean)

    @Query("DELETE FROM astrophotoentity")
    suspend fun clearAstroPhotos()

   // @Query("UPDATE astrophotoentity SET isFavorite = NOT isFavorite WHERE id=:id")
    
    //FAVPHOTOENTITY
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoritePhoto(photo: FavPhotoEntity)

    @Query("SELECT * FROM favphotoentity WHERE id =:id")
    suspend fun getFavPhotoById(id: String): FavPhotoEntity?

    @Query("SELECT * FROM favphotoentity WHERE timeStamp BETWEEN :startDate AND :endDate ORDER BY timeStamp ASC ")
    fun getFavPhotos(startDate: Long, endDate:Long): Flow<List<FavPhotoEntity>>

    @Delete
    suspend fun deleteFavPhoto(photo: FavPhotoEntity)

    @Query("SELECT EXISTS(SELECT 1 FROM favphotoentity WHERE id=:id)")
    suspend fun isFavPhotoSavedCheck(id: String): Boolean


}
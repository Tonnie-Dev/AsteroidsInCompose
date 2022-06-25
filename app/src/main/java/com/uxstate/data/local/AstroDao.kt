package com.uxstate.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface AstroDao {


    //ASTROPHOTOENTITY
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAstroPhotos(list: List<AstroPhotoEntity>)

    @Query("SELECT * FROM astrophotoentity")
    suspend fun getSavedAstroPhotos(): List<AstroPhotoEntity>

    @Query("DELETE FROM astrophotoentity")
    suspend fun clearAstroPhotos()

    //FAVPHOTOENTITY
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoritePhoto(photo: FavPhotoEntity)

    @Query("SELECT * FROM favphotoentity WHERE id =:id")
    suspend fun getFavPhotoById(id: String): FavPhotoEntity?

    @Query("SELECT * FROM astrophotoentity ")
    fun getFavPhotos(): Flow<List<FavPhotoEntity>?>

    @Delete
    suspend fun deleteFavPhoto(photo: FavPhotoEntity)

    @Query("SELECT EXISTS(SELECT 1 FROM favphotoentity WHERE id=:id)")
    suspend fun isFavPhotoSavedCheck(id: String): Boolean


}
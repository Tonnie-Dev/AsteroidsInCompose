package com.uxstate.domain.use_cases

data class UseCaseContainer(
    val deleteFavoritePhotoUseCase: DeleteFavoritePhotoUseCase,
    val getFavAstroPhotoUseCase: GetFavAstroPhotoUseCase,
    val getAstroPhotosUseCase: GetAstroPhotosUseCase,
    val getFavAstroPhotosUseCase:GetFavAstroPhotosUseCase,
    val insertAstroPhotoUseCase: InsertAstroPhotoUseCase
)
package com.uxstate.domain.use_cases

data class UseCaseContainer(
    val deleteFavoritePhotoUseCase: DeleteFavoritePhotoUseCase,
    val getAstroPhotosUseCase: GetAstroPhotosUseCase,
    val getFavAstroPhotosUseCase:GetFavAstroPhotosUseCase,
    val insertAstroPhotoUseCase: InsertAstroPhotoUseCase,
    val updateIsFavoriteStatus: UpdateIsFavoriteStatusUseCase,
    val getLiveAstroPhotosUseCase: GetLiveAstroPhotosUseCase
)

package com.lpirro.cryptomovies.data.network.model

import com.google.gson.annotations.SerializedName

data class MovieDetailsDto(
    @SerializedName("overview") val overview: String,
    @SerializedName("genres") val genres: List<GenresDto>,
    @SerializedName("credits") val credits: CreditsDto,
    @SerializedName("original_title") val originalTitle: String,
    @SerializedName("status") val status: String,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("production_companies") val productionCompanies: List<ProductionCompaniesDto>,
    @SerializedName("budget") val budget: Int,
    @SerializedName("revenue") val revenue: Int
)

data class GenresDto(@SerializedName("id") val id: Int, @SerializedName("name") val name: String)

data class CreditsDto(@SerializedName("cast") val cast: List<CastDto>)

data class CastDto(@SerializedName("profile_path") val profileImageUrl: String)

data class ProductionCompaniesDto(@SerializedName("name") val name: String)

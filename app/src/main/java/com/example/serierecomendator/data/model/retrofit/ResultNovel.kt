package com.example.serierecomendator.data.model.retrofit

data class ResultNovel(
    val covers: List<Cover>,
    val description: String,
    val extra_metadata: ExtraMetadata,
    val id: Int,
    val latest_published: Double,
    val rating: Double,
    val rating_count: Int,
    val release_count: Int,
    val title: String,
    val tl_type: String
)
data class ExtraMetadata(
    val is_yaoi: Boolean,
    val is_yuri: Boolean
)
data class Cover(
    val chapter: Any,
    val description: String,
    val url: String,
    val volume: Any
)
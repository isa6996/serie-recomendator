package com.example.serierecomendator.data.model.retrofit

data class ResultWebtoon(
    val message: Message
)

data class Message(
    val type: String,
    val service: String,
    val version: String,
    val result: ChallengeSearchResult
)

data class ChallengeSearchResult(
    val challengeSearch: SearchedWebtoon
)

data class WebtoonTitle(
    val lastEpisodeRegisterYmdt: Long,
    val likeitCount: Int,
    val newTitle: Boolean,
    val readCount: Int,
    val registerYmdt: Long,
    val representGenre: String,
    val serviceStatus: String,
    val starScoreAverage: Double,
    val thumbnail: String,
    val title: String,
    val titleNo: Int,
    val totalServiceEpisodeCount: Int,
    val writingAuthorName: String
)

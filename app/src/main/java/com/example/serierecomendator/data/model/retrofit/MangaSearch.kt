package com.example.serierecomendator.data.model.retrofit

data class MangaSearchList(
    val `data`: List<Data>,
    val limit: Int,
    val offset: Int,
    val response: String,
    val result: String,
    val total: Int
)
data class Title(
    val en: String
)
data class Tag(
    val attributes: AttributesX,
    val id: String,
    val relationships: List<Any>,
    val type: String
)
data class Relationship(
    val attributes: AttributesXX,
    val id: String,
    val related: String,
    val type: String
)
data class Name(
    val en: String
)
data class Links(
    val al: String,
    val amz: String,
    val ap: String,
    val bw: String,
    val cdj: String,
    val ebj: String,
    val engtl: String,
    val kt: String,
    val mal: String,
    val mu: String,
    val nu: String,
    val raw: String
)
data class AltTitle(
    val en: String,
    val es: String,
  //  val es-la: String,
    val fa: String,
    val fr: String,
    val id: String,
    val ja: String,
//    val ja-ro: String,
    val ko: String,
    val mn: String,
 //   val pt-br: String,
    val ru: String,
    val th: String,
    val tr: String,
    val vi: String,
    val zh: String,
/*    val zh-hk: String,
    val zh-ro: String*/
)

data class Attributes(
    val altTitles: List<AltTitle>,
    val availableTranslatedLanguages: List<String>,
    val chapterNumbersResetOnNewVolume: Boolean,
    val contentRating: String,
    val createdAt: String,
    val description: Description,
    val isLocked: Boolean,
    val lastChapter: String,
    val lastVolume: String,
    val latestUploadedChapter: String,
    val links: Links,
    val originalLanguage: String,
    val publicationDemographic: String,
    val state: String,
    val status: String,
    val tags: List<Tag>,
    val title: Title,
    val updatedAt: String,
    val version: Int,
    val year: Int
)
data class AttributesX(
    val description: DescriptionX,
    val group: String,
    val name: Name,
    val version: Int
)
data class AttributesXX(
    val createdAt: String,
    val description: String,
    val fileName: String,
    val locale: String,
    val updatedAt: String,
    val version: Int,
    val volume: String
)
data class Data(
    val attributes: Attributes,
    val id: String,
    val relationships: List<Relationship>,
    val type: String
)
data class Description(
    val en: String,
    val es: String,
  //  val es-la: String,
    val fr: String,
    val ja: String,
    val pt: String,
  //  val pt-br: String,
    val ru: String,
    val th: String,
    val vi: String,
    val zh: String,
 //   val zh-hk: String
)
class DescriptionX
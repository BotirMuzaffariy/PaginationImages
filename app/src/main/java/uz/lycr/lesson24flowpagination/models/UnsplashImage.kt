package uz.lycr.lesson24flowpagination.models

data class UnsplashImage(
    val results: List<Result>,
    val total: Int,
    val total_pages: Int,
    val category: String
)
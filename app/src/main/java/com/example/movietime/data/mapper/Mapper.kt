package com.example.movietime.data.mapper

import com.example.movietime.domain.models.ReviewsEntity
import com.example.movietime.domain.models.ReviewsResponse


fun ReviewsResponse.asReviewsEntity() : List<ReviewsEntity> {
    return results.map {
            ReviewsEntity(
                imageSrc = it.multimedia.src,
                title = it.display_title,
                review = it.summary_short
            )
    }
}
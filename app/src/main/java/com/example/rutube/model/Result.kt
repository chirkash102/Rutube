package com.example.rutube.model

import com.example.rutube.model.ActionReason
import com.example.rutube.model.Author
import com.example.rutube.model.Category
import com.example.rutube.model.PgRating

data class Result(
    val action_reason: ActionReason,
    val author: Author,
    val category: Category,
    val created_ts: String,
    val description: String,
    val duration: Int,
    val embed_url: String,
    val feed_name: String,
    val feed_url: String,
    val hits: Int,
    val html: String,
    val id: String,
    val is_adult: Boolean,
    val is_audio: Boolean,
    val is_classic: Boolean,
    val is_club: Boolean,
    val is_licensed: Boolean,
    val is_livestream: Boolean,
    val is_official: Boolean,
    val is_original_content: Boolean,
    val is_original_sticker_2x2: Boolean,
    val is_paid: Boolean,
    val kind_sign_for_user: Boolean,
    val last_update_ts: String,
    val origin_type: String,
    val pg_rating: PgRating,
    val picture_url: String,
    val position: Int,
    val preview_url: String,
    val product_id: Int,
    val publication_ts: String,
    val stream_type: Any,
    val thumbnail_url: String,
    val title: String,
    val track_id: Int,
    val video_url: String
)
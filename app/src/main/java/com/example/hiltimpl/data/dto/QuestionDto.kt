package com.example.hiltimpl.data.dto

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class QuestionDto(

    @Keep var id:String,
    @Keep var lessonId:String,
    @Keep var title:String,
    @Keep var typeId:String,
    @Keep var score:String,
    @Keep var difficulty:String,
    @Keep var backgroundPhotoUrl:String,
    @Keep var photoUrl:String,
    @Keep var audioUrl:String,

    @Keep var dragAndDropBucketOptions:List<DragAndDropBucketOptionsQuestionDto> ?= null,
    @Keep var multiSelectableOptions:MultiSelectableOptionsQuestionDto?= null,
    @Keep var relatableOptions:RelatableOptionsQuestionDto?= null,
    @Keep var selectableBucketOptions:SelectableBucketOptionsQuestionDto?= null,
    @Keep var selectableOptions:SelectableOptionsQuestionDto?= null,

):Parcelable {
}

@Keep
@Parcelize
data class DragAndDropBucketOptionsQuestionDto(

    @Keep var id:String,
    @Keep var Title:String,
    @Keep var QuestionId:String,
    @Keep var PhotoUrl:String,
    @Keep var DragAndDropBucketItems:List<DragAndDropBucketItemsDto>,

):Parcelable


@Keep
@Parcelize
data class DragAndDropBucketItemsDto(

    @Keep var id:String,
    @Keep var title:String,
    @Keep var count:String,
    @Keep var bucketOptionId:String,
    @Keep var photoUrl:String,

) :Parcelable


@Keep
@Parcelize
data class MultiSelectableOptionsQuestionDto(

    @Keep var id:String,
    @Keep var questionId:String,
    @Keep var title:String,
    @Keep var photoUrl:String,
    @Keep var isNumeric:Boolean,
    @Keep var answer:Boolean,

):Parcelable

@Keep
@Parcelize
data class RelatableOptionsQuestionDto(

    @Keep var id:String,
    @Keep var questionId:String,
    @Keep var title:String,
    @Keep var matchId:String,
    @Keep var isFirstColumn:Boolean,
    @Keep var photoUrl:String,

):Parcelable

@Keep
@Parcelize
data class SelectableBucketOptionsQuestionDto(

    @Keep var id:String,

):Parcelable

@Keep
@Parcelize
data class SelectableOptionsQuestionDto(

    @Keep var id:String,
    @Keep var questionId:String,
    @Keep var title:String,
    @Keep var isCorrect:Boolean,
    @Keep var photoUrl:String,

):Parcelable

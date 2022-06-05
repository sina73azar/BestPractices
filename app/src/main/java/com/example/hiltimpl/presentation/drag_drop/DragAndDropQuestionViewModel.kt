package com.example.hiltimpl.presentation.drag_drop

import androidx.lifecycle.ViewModel
import com.example.hiltimpl.data.dto.DragAndDropBucketItemsDto
import com.example.hiltimpl.data.dto.DragAndDropBucketOptionsQuestionDto
import com.example.hiltimpl.data.dto.QuestionDto
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DragAndDropQuestionViewModel @Inject constructor(

) :ViewModel(){

    fun getQuestion():QuestionDto{
        val bucketItem1 = DragAndDropBucketItemsDto(
            "1",
            "angoor",
            "2",
            "",
            "R.drawable.angoor"
        )
        val bucketItems2 = DragAndDropBucketItemsDto(
            "2",
            "pears",
            "1",
            "",
            "R.drawable.pears"
        )
        val bucketItems3 = DragAndDropBucketItemsDto(
            "3",
            "watermelon",
            "1",
            "",
            "R.drawable.watermelon"
        )
        val bucketOptions = DragAndDropBucketOptionsQuestionDto(
            "1",
            "basket option",
            "question1",
            "R.drawable.ic_sabad",
            listOf(bucketItem1, bucketItems2, bucketItems3)
        )
        return QuestionDto(
            "1", "2", "question title", "1", "5", "16",
            "",
            "",
            "",
            listOf(bucketOptions),
            null,
            null,
            null,
            null

        )

    }
}
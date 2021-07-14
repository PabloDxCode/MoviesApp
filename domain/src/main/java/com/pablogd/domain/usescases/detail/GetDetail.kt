package com.pablogd.domain.usescases.detail

import com.pablogd.domain.Result
import com.pablogd.domain.models.Detail
import com.pablogd.domain.repositories.DetailRepository

class GetDetail(private val localDetailRepository: DetailRepository) {

    suspend fun invoke(): Result<Detail> = localDetailRepository.getDetail()

}
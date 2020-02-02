package com.andigeeky.movies.presentation.common

open class ViewState<E, R>(
    val loading: Boolean = false,
    val error: E? = null,
    val result: R? = null
) : BaseViewState

interface BaseViewState

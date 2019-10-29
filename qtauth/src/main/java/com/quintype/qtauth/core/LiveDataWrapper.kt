package com.quintype.qtauth.core

data class LiveDataWrapper<T>(
    var data: T? = null,
    var error: Throwable? = null
)
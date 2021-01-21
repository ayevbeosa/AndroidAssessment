package com.example.android.androidassessment.data.utils

interface Mapper<Local, Remote> {

    fun Local.toRemote(): Remote

    fun List<Local>.toRemote(): List<Remote> = this.map { it.toRemote() }

    fun Remote.toLocal(): Local

    fun List<Remote>.toLocal(): List<Local> = this.map { it.toLocal() }
}
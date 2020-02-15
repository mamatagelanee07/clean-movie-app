package com.andigeeky.movies.presentation.common

sealed class TaskStatus

object SUCCESS : TaskStatus()
object FAILURE : TaskStatus()
object LOADING : TaskStatus()

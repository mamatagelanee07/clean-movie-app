package com.andigeeky.movies.presentation.popular

sealed class TaskStatus

object SUCCESS : TaskStatus()
object FAILURE : TaskStatus()
object LOADING : TaskStatus()

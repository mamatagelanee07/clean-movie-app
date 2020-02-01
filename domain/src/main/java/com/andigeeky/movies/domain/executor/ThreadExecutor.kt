package com.andigeeky.movies.domain.executor

import java.util.concurrent.Executor

/**
 * Executor implementation can be based on different frameworks or techniques of asynchronous
 * execution, but every implementation will execute the use cases
 */
interface ThreadExecutor : Executor

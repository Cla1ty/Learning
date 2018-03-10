package com.dwiariyanto.reactivex.create

import com.dwiariyanto.reactivex.execute
import io.reactivex.Observable
import org.junit.Test
import java.util.concurrent.TimeUnit

class Timer{
    @Test fun timer()
    {
        Observable
                .timer(2, TimeUnit.SECONDS)
                .execute()
    }
}

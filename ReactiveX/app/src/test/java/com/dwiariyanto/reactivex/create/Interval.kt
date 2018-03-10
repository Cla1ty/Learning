package com.dwiariyanto.reactivex.create

import com.dwiariyanto.reactivex.execute
import io.reactivex.Observable
import org.junit.Test
import java.util.concurrent.TimeUnit

class Interval{
    @Test fun interval()
    {
        Observable
                .interval(1, TimeUnit.SECONDS)
                .timeInterval()
                .take(5)
                .execute()
    }
}

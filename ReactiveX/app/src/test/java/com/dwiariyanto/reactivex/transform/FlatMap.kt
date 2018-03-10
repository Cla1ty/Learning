package com.dwiariyanto.reactivex.transform

import com.dwiariyanto.reactivex.execute
import io.reactivex.Observable
import org.junit.Test

class FlatMap
{
    /**
     * Next 1
     * Next 4
     * Next 9
     * Next 16
     * Next 25
     * Complete
     */
    @Test fun flatMap()
    {
        Observable
                .range(1, 5)
                .flatMap { Observable.just(it * it) }
                .execute()
    }
}

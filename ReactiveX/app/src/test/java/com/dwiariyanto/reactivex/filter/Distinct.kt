package com.dwiariyanto.reactivex.filter

import com.dwiariyanto.reactivex.execute
import io.reactivex.Observable
import org.junit.Test

class Distinct
{
    /**
     * Next 1
     * Next 2
     * Next 3
     * Complete
     */
    @Test fun dis()
    {
        Observable
                .just(1, 2, 1, 1, 2, 3)
                .distinct()
                .execute()
    }
}

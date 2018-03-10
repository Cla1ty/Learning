package com.dwiariyanto.reactivex.create

import com.dwiariyanto.reactivex.execute
import io.reactivex.Observable
import org.junit.Test

class Range{
    /**
     * Next 1
     * Next 2
     * Next 3
     * Next 4
     * Next 5
     * Next 6
     * Next 7
     * Next 8
     * Next 9
     * Next 10
     * Complete
     */
    @Test fun range()
    {
        Observable
                .range(1, 10)
                .execute()
    }
}

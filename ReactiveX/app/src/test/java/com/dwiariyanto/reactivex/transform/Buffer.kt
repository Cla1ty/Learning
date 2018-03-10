package com.dwiariyanto.reactivex.transform

import com.dwiariyanto.reactivex.execute
import io.reactivex.Observable
import org.junit.Test

class Buffer
{
    /**
     * Next [1, 2, 3]
     * Next [4, 5, 6]
     * Next [7, 8, 9]
     * Next [10]
     * Complete
     */
    @Test fun bufferCount()
    {
        Observable
                .range(1, 10)
                .buffer(3)
                .execute()
    }

    /**
     * Next [1, 2]
     * Next [5, 6]
     * Next [9, 10]
     * Complete
     */
    @Test fun bufferCountSkip()
    {
        Observable
                .range(1, 10)
                .buffer(2, 4)
                .execute()
    }
}


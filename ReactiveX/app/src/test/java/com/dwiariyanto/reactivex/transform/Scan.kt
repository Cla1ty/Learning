package com.dwiariyanto.reactivex.transform

import com.dwiariyanto.reactivex.execute
import io.reactivex.Observable
import org.junit.Test

class Scan
{
    /**
     * Next 1
     * Next 3
     * Next 6
     * Next 10
     * Next 15
     * Complete
     */
    @Test fun scan()
    {
        Observable
                .range(1, 5)
                .scan { previousResult: Int, value: Int ->
                    previousResult + value
                }
                .execute()
    }
}

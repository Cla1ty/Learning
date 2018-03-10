package com.dwiariyanto.reactivex.create

import com.dwiariyanto.reactivex.execute
import io.reactivex.Observable
import org.junit.Test

class From
{
    /**
     * Next Sven
     * Next Invoker
     * Next Line
     * Complete
     */
    @Test fun from()
    {
        Observable
                .fromArray("Sven", "Invoker", "Line")
                .execute()
    }
}

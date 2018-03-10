package com.dwiariyanto.reactivex.create

import com.dwiariyanto.reactivex.execute
import io.reactivex.Observable
import org.junit.Test

class Empty{

    /**
     * Complete
     */
    @Test fun empty()
    {
        Observable
                .empty<String>()
                .execute()
    }
}
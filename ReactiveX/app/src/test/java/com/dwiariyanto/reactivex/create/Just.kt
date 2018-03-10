package com.dwiariyanto.reactivex.create

import com.dwiariyanto.reactivex.execute
import io.reactivex.Observable
import org.junit.Test

class Just{
    /**
     * Next Pudge
     * Next Night stalker
     * Complete
     */
    @Test fun just()
    {
        Observable
                .just("Pudge", "Night stalker")
                .execute()
    }
}

package com.dwiariyanto.reactivex.create

import com.dwiariyanto.reactivex.execute
import io.reactivex.Observable
import org.junit.Test

class Repeat
{
    @Test fun repeat()
    {
        Observable
                .just("Queen Of Pain")
                .repeat(5)
                .execute()
    }
}

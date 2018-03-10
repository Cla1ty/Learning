package com.dwiariyanto.reactivex.create

import com.dwiariyanto.reactivex.execute
import io.reactivex.Observable
import org.junit.Test

class Never{
    /**
     *
     */
    @Test fun never()
    {
        Observable
                .never<String>()
                .execute()
    }
}

package com.dwiariyanto.backstack.extension

object Logcat
{
    fun clear()
    {
        ProcessBuilder()
                .command("logcat", "-c")
                .redirectErrorStream(true)
                .start()
    }
}
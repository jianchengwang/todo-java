我们可以把协程简单地理解为一种轻量级的线程。从操作系统的角度来看，线程是在内核态中调度的，而协程是在用户态调度的，所以相对于线程来说，协程切换的成本更低。协程虽然也有自己的栈，但是相比线程栈要小得多，典型的线程栈大小差不多有 1M，而协程栈的大小往往只有几 K 或者几十 K。所以，无论是从时间维度还是空间维度来看，协程都比线程轻量得多。

https://vorpus.org/blog/notes-on-structured-concurrency-or-go-statement-considered-harmful/

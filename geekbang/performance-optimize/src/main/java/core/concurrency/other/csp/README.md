Golang 提供了两种不同的方案：一种方案支持协程之间以共享内存的方式通信，Golang 提供了管程和原子类来对协程进行同步控制，这个方案与 Java 语言类似；另一种方案支持协程之间以消息传递（Message-Passing）的方式通信，本质上是要避免共享，Golang 的这个方案是基于 CSP（Communicating Sequential Processes）模型实现的

，Golang 程序员中有句格言：“不要以共享内存方式通信，要以通信方式共享内存（Don’t communicate by sharing memory, share memory by communicating）。

Golang 中协程之间通信推荐的是使用 channel

http://www.usingcsp.com/cspbook.pdf



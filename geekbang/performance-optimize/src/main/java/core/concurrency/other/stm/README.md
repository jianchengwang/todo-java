其实很多编程语言都有从数据库的事务管理中获得灵感，并且总结出了一个新的并发解决方案：软件事务内存（Software Transactional Memory，简称 STM）。传统的数据库事务，支持 4 个特性：原子性（Atomicity）、一致性（Consistency）、隔离性（Isolation）和持久性（Durability），也就是大家常说的 ACID，STM 由于不涉及到持久化，所以只支持 ACI。

数据库事务发展了几十年了，目前被广泛使用的是 MVCC（全称是 Multi-Version Concurrency Control），也就是多版本并发控制。

MVCC 可以简单地理解为数据库事务在开启的时候，会给数据库打一个快照，以后所有的读写都是基于这个快照的。

当提交事务的时候，如果所有读写过的数据在该事务执行期间没有发生过变化，那么就可以提交；如果发生了变化，说明该事务和有其他事务读写的数据冲突了，这个时候是不可以提交的。

https://github.com/epam-mooc/stm-java
http://www.codecommit.com/blog/scala/improving-the-stm-multi-version-concurrency-control

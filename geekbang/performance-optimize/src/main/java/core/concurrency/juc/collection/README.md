在容器领域一个容易被忽视的“坑”是用迭代器遍历容器，例如在下面的代码中，通过迭代器遍历容器 list，对每个元素调用 foo() 方法，这就存在并发问题，这些组合的操作不具备原子性。

正确做法是下面这样，锁住 list 之后再执行遍历操作。如果你查看 Collections 内部的包装类源码，你会发现包装类的公共方法锁的是对象的 this，其实就是我们这里的 list，所以锁住 list 绝对是线程安全的。

List 里面只有一个实现类就是 CopyOnWriteArrayList。CopyOnWrite，顾名思义就是写的时候会将共享变量新复制一份出来，这样做的好处是读操作完全无锁。
使用 CopyOnWriteArrayList 需要注意的“坑”主要有两个方面。一个是应用场景，CopyOnWriteArrayList 仅适用于写操作非常少的场景，而且能够容忍读写的短暂不一致。例如上面的例子中，写入的新元素并不能立刻被遍历到。另一个需要注意的是，CopyOnWriteArrayList 迭代器是只读的，不支持增删改。
因为迭代器遍历的仅仅是一个快照，而对快照进行增删改是没有意义的。

Map 接口的两个实现是 ConcurrentHashMap 和 ConcurrentSkipListMap，
它们从应用的角度来看，主要区别在于 ConcurrentHashMap 的 key 是无序的，而 ConcurrentSkipListMap 的 key 是有序的。所以如果你需要保证 key 的顺序，就只能使用 ConcurrentSkipListMap。
ConcurrentSkipListMap 里面的 SkipList 本身就是一种数据结构，中文一般都翻译为“跳表”。跳表插入、删除、查询操作平均的时间复杂度是 O(log n)，理论上和并发线程数没有关系，所以在并发度非常高的情况下，若你对 ConcurrentHashMap 的性能还不满意，可以尝试一下 ConcurrentSkipListMap。

Set 接口的两个实现是 CopyOnWriteArraySet 和 ConcurrentSkipListSet，使用场景可以参考前面讲述的 CopyOnWriteArrayList 和 ConcurrentSkipListMap，它们的原理都是一样的，这里就不再赘述了。

Java 并发包里面 Queue 这类并发容器是最复杂的，你可以从以下两个维度来分类。
一个维度是阻塞与非阻塞，所谓阻塞指的是当队列已满时，入队操作阻塞；当队列已空时，出队操作阻塞。
另一个维度是单端与双端，单端指的是只能队尾入队，队首出队；而双端指的是队首队尾皆可入队出队。
Java 并发包里阻塞队列都用 Blocking 关键字标识，单端队列使用 Queue 标识，双端队列使用 Deque 标识。

实际工作中，一般都不建议使用无界的队列，因为数据量大了之后很容易导致 OOM。
上面我们提到的这些 Queue 中，只有 ArrayBlockingQueue 和 LinkedBlockingQueue 是支持有界的，所以在使用其他无界队列时，一定要充分考虑是否存在导致 OOM 的隐患。

FAIL-FAST: 当一个线程遍历容器时，有另一个线程对其修改就会触发fail fast机制，遍历容器的线程也会抛出异常，
本质是为了确保遍历时的线程安全，容器内部维护一个初始化为modCount的expectedModeCount变量，遍历时会去检查该期望值是否等于modCount，不等于就说明出现了修改集合操作





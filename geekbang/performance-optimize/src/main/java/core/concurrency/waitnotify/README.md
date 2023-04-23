被通知的线程要想重新执行，仍然需要获取到互斥锁（因为曾经获取的锁在调用 wait() 时已经释放了）。
上面我们一直强调 wait()、notify()、notifyAll() 方法操作的等待队列是互斥锁的等待队列，
所以如果 synchronized 锁定的是 this，那么对应的一定是 this.wait()、this.notify()、this.notifyAll()；
如果 synchronized 锁定的是 target，那么对应的一定是 target.wait()、target.notify()、target.notifyAll() 。
而且 wait()、notify()、notifyAll() 这三个方法能够被调用的前提是已经获取了相应的互斥锁，所以我们会发现 wait()、notify()、notifyAll() 都是在 synchronized{}内部被调用的。
如果在 synchronized{}外部调用，或者锁定的 this，而用 target.wait() 调用的话，JVM 会抛出一个运行时异常：java.lang.IllegalMonitorStateException。

wait()方法与sleep()方法的不同之处在于，wait()方法会释放对象的“锁标志”。
sleep是Thread的方法，而wait是Object类的方法；sleep方法调用的时候必须指定时间
当调用某一对象的wait()方法后，会使当前线程暂停执行，并将当前线程放入对象等待池中，
直到调用了notify()方法后，将从对象等待池中移出任意一个线程并放入锁标志等待池中，只有锁标志等待池中的线程可以获取锁标志，它们随时准备争夺锁的拥有权。
当调用了某个对象的notifyAll()方法，会将对象等待池中的所有线程都移动到该对象的锁标志等待池。
sleep()方法需要指定等待的时间，它可以让当前正在执行的线程在指定的时间内暂停执行，进入阻塞状态，
该方法既可以让其他同优先级或者高优先级的线程得到执行的机会，也可以让低优先级的线程得到执行机会。
但是sleep()方法不会释放“锁标志”，也就是说如果有synchronized同步块，其他线程仍然不能访问共享数据。

### 基本概念
1. 同步(Synchronous)和异步(Asynchronous)
2. 并发(Concurrency)和并行(Parallelism)
* 并发偏重于多个任务交替执行，而多个任务之间有可能还是串行的。而并行是真正意义上的"同时执行"

3. 临界区:  表示用一种公共资源或是共享数据
* 可以被多个线程使用
* 但一次只能有一个线程使用；后续想使用只能等待

4. 阻塞(Blocking)和 非阻塞(Non-Blocking): 
* 访问临界区时，若已被其他线程占用，则线程挂起等待。称为 线程阻塞

5. 死锁(DeadLock): 线程互相占用各自需要的资源
6. 饥饿(Starvation): 线程由于原因无法获得所需资源，如

* 线程优先级太低，资源释放时，抢不过高优先级的线程
* 线程请求资源，被一个线程长期占用，始终无法得到
* 与死锁相比，饥饿还是有可能在未来一段时间解决的

7. 活锁(LiveLock)：线程主动将资源释放给他人使用，那么就会出现资源不断在两个线程之间跳动，却没有一个线程可以同时拿到所有资源而正常执行的。

### 并发级别
1. 阻塞(Blocking): 
- synchronized, ReentrantLock,得到临界区的锁之前，线程挂起。
2. 无饥饿(Starvation-Free)
- 公平锁
3. 无障碍(Obstruction-Free)
- 乐观锁
4. 无锁(Lock-Free)
- CAS
5. 无等待
- RCU(Read-Copy-Update)， 读无需等待，写需要等待

### 并行的两个定律
1. Amdahl 定律
* 加速比 = 优化前系统耗时 / 优化后系统耗时
* F = 串行执行的比例， n = CPU数
* 加速比 = 1 / (F + 1/n *(1-F)
* 结论：当串行比较一定时，加速比是有上限的，再多加CPU也不能突破上限。

2. Gustafson定律
* 加速比 = n - F * (n - 1)
* 结论: 并行化的代码所占比重足够多，则加速比就能随着CPU数量线性增长。

### 多线程协同工作遵循的原则
1. 原子性:   32位系统操作一个Long(64位)，非原子操作
2. 可见性:   volatile
3. 有序性:   指令重排

### 线程状态:
```
   public enum State {
       NEW,
       RUNNABLE,
       BLOCKED,
       WAITING,
       TIMED_WAITING,
       TERMINATED
   }
```

### 线程的基本操作
1. 新建线程
```
    Thread t1 = new Thread() {
        @Override
        public void run() {
            // TODO
        }
    }
    t1.start();// 不能使用t1.run(),它只在当前线程做run()操作
```
```
    public interface Runnable {
        abstract void run();
    }
    // 实现 Runnalbe 接口
```
2. 终止线程

> Thread.stop()会直接终止线程，并且会立即释放这个线程的所有锁，而该锁正好是保证数据一致性的。 若数据写到一半线程被stop，锁放开，其他线程就读到一个错误数据。

```
    public volatile boolean stopme = false;
    public void stopMe() {
        stopme = true;
    }
    @Override
    public void run() {
        while(true) {
            if (stopme) {
                break;
            }
            synchronized(u) {
            //正常逻辑
            }
        }
    }
    
    // 终止时，只需 t1.stopMe()
```
3. 线程中断: 
```
public void Thread.interrupt() //中断线程
public boolean Thread.isInterrupted() //判断是否被中断
public static boolean Thread.interrupted() //判断是否被中断,并清除中断标志
public static native void sleep(long millis) throw InterruptedException
```
4. 等待和通知  wait&notify

> 并不是线程的方法，而是每个对象都有 wait(), notify()

> Object.wait() Object.notify()必须配合synchronized使用

```
 ==> thread1
   public void run() {
       synchronized(object) {
           object.wait()
           // do something
       }
   }
   
 ==> thread2
   public void run() {
       synchronized(object) {
           // do something 
           object.notify()
       }
   }
```

5. 线程的挂起(suspend) 和 恢复(resume)  --- 由于可能造成线程永远挂起，不推荐
6. 等待线程结束(join) 和 谦让(yield)
```
    t1.join(); // 等待t1执行完再继续执行
    Thread.yield(); // 让出CPU执行
```
* 如果觉得线程不那么重要，或者优先级非常低，怕它占用太多CPU资源，可以适当的时候调用Thread.yield(),给予其他重要线程更多的工作的机会。

### volatile 与 JMM

> 用volatile去申明一个变量时，就等于告诉JVM，这个变量极有可能会被某线程修改，为了确保这个变量被修改后，应用程序范围内的所有线程都能够"看到"这个改动，JVM必须采用一些特殊手段来保证这个变量的可见性

> volatile 对于保证操作的原子性有非常大的作用，但不能替代锁。且它是线程不安全的。

### 线程组:
```
    ThreadGroup tg = new ThreadGroup("hello");
    Thread t1 = new Thread(tg, Runnable, "T1");
    
```

> ThreadGroup.activeCount(), ThreadGroup.list()

> 默认ThreadGroup.  为父类的ThreadGroup, main

### 守护线程

> 当一个Java应用只有 守护线程时，JVM会自动退出

> GC , JIT线程增多为 守护线程

```
    Thread t1 = new Thread("T1");
    t1.setDaemon(true)
    
```

### 线程优先级

```
    Thread.MAX_PRIORITY  = 10
    Thread.NORM_PRIORITY  = 5
    Thread.MIN_PRIORITY  = 1
    
    t1.setPriority(Thread.MAX_PRIORITY)
```

## Java 并发包

### 同步控制
1. ReentrantLock

```
    private static ReentrantLock lock = new ReentrantLock();
    
    lock.lock();
    try {
        //TODO 
    } finally {
        lock.unlock();
    }
```

> lock.lock() 和 lock.unlock() 要次数上对应，否则锁无法被释放

> 重入锁的高级特性

- 中断响应:   lock.lockInterruptibly();   &    thread.interrupt();
- 锁申请等待限时: lock.tryLock(5, TimeUnit.SECONDS) & lock.tryLock()
- 公平锁: private ReentrantLock lock = new ReentrantLock(true);

> Condition条件实现 Object.wait() ,Object.notify()功能, 结合重入锁使用

```
    public static ReentrantLock lock = new ReentrantLock();
    public static Condition condition = lock.newCondition();
    ===================
    try {
        lock.lock();
        //TODO 
        condition.await();
    } finally {
        lock.unlock();
    }
    ===================
    lock.lock();
    condition.signal();
    lock.unlock();
```

2. 信号量 Semaphore

> synchronized 和 重入锁 均是一次只允许一个线程访问一个资源；信号量却可以指定多个线程，同时访问某一个资源。

```
    public Semaphore semaphore = new Semaphore(5);// 5个线程同时访问资源
    ===================
    try {
        semaphore.acquire(); // 获取一个许可
        //TODO
        semaphore.release(); // 释放一个许可
    }
```

3. ReadWriteLock 读写锁

> 读读操作不需要加锁， 写读 和 读写才需要加锁。 保证读读操作的并行

4. CountDownLatch

```
    private static CountDownLatch downLatch = new CountDownLatch(10);
    ============================
    downLatch.countDown(); // 计数器  -1
    downLatch.await(); // 挂起直到 计数器 为0 
```

5. CyclicBarrier

```
    public CyclicBarrier(int parties, Runnable barrierAction)
    parties表示等待多少个任务执行完成，barrierAction任务执行完时的回调
    
    cb.await() // 等待所有任务完成，并调用回调
```

6. LockSupport
```
    LockSupport.park();
    LockSupport.unpark(t2);
```

> unpark 在 park 之前调用，不会引起线程永远waiting

> park支持中断，但不会抛 InterruptedException，但可以通过Thread.interrupted()进行查看。

### 线程池
1. 为什么使用线程池:

- 创建和关闭线程也需要花费时间，如果任务小，会出去创建线程比程序运行时间还长
- 线程本身也占用内存空间，大量的线程会导致OOM，即使没异常，也会给GC带来巨大压力

2. JDK的 Executor框架
- newFixedThreadPool(): 线程池大小固定，有新的线程提交，若有空闲线程，就执行；若没有，则进入任务队列，待有线程空闲再执行
- newSingleThreadExecutor(): 只有一个线程的线程池。多于一个线程提交，会先入任务队列，待空闲，按先入先出执行任务
- newCachedThreadPool(): 线程池大小不固定，有空闲复用则复用，没有空闲且有新任务提交，则创建新线程进行处理。完成后交还给线程池，相当于线程池增大
- newSingleThreadScheduledExecutor(): 比newSingleThreadExecutor多增加给定时间执行的功能
- newScheduledThreadPool(): 指定线程数量的 ScheduledThreadPool.
- 计划任务, .schedule()   .scheduleAtFixedRate()  .scheduleWithFixedDelay()

3. Executor具体实现:
- 以上方法均来自于 ThreadPoolExecutor的不同版本的封装
```
    public ThreadPoolExecutor(
            int corePoolSize,  //线程数量
            int maximumPoolSize, //最大线程数量
            long keepAliveTime, // 超过corePoolSize的线程，多长时间会被销毁
            TimeUnit unit, // 时间单位
            BlockingQueue<Runnable> wordQueue, //被提交但尚为执行的任务队列
            ThreadFactory factory, //线程工厂
            RejectExecutionHandler handler//拒绝策略，来不及处理如何拒绝任务
            )
```
- 几种BlockingQueue:

> SynchronousQueue: 提交任务不会被真实保存，而总是将新任务提交给线程执行，如果没有空闲线程，则创建；如果达到maximumPoolSize，则拒绝。

> ArrayBlockingQueue(int capacity): 有界，如果新任务提交，当前线程数小于corePoolSize,则创建线程执行；如果大于corePoolSize,则进入等待队列。若等待队列已满(>= capacity)，则总线程数不大于maximumPoolSize，创建新线程执行；若大于maximumPoolSize，则拒绝。

> LinkedBlockingQueue: 无界，如果新任务提交，当前线程数小于corePoolSize,则创建线程执行；如果大于corePoolSize,则进入等待队列，无限进入等待队列。

> PriorityBlockingQueue: 无界，有优先级。

- ThreadPoolExecutor的任务调度逻辑

> 任务提交后
> 1. 小于corePoolSize，分配执行
> 2. 大于corePoolSize, 提交到等待队列
> 3. 提交等待队列成功，执行
> 4. 提交失败，提交线程池
> 5. 达到maxPoolSize, 拒绝
> 6. 未达到maxPoolSize, 分配线程执行

- 拒绝策略:

> 1. AbortPolicy: 直接抛异常，阻止系统正常工作
> 2. CallerRunsPolicy: 在调用者的线程上跑这个任务
> 3. DiscardOldestPolicy: 丢弃等待队列中最老的，再次提交当前任务
> 4. DiscardPolicy: 默默丢弃任务，不予处理

- 最优线程池大小:

> N_cpu = CPU 数量， 
> U_cpu = 目标CPU使用率, 0 =< Ucpu < 1, 
> W/C=等待时间与计算时间比值
> N_threads = N_cpu * U_cpu * (1 + W/C)

- 用 threadpool.execute() 代替 submit()， 可以看到更多异常信息。




## 锁优化
### 锁优化的几点建议:
1. 减少持锁时间

```
    public synchronized void syncMethord() {
        somecode();
        mutexMehord();
        othercode();
    }
    
修改为 ====>

    public void syncMethord() {
        somecode();
        synchronized(this) {
            mutexMehord();
        }
        othercode();
    }
```

2. 减小锁粒度: 典型使用场景是 ConcurrentHashMap.

> 对于HashMap加锁，一种就是对整个HashMap加锁

> ConcurrentHashMap细分了若干个小的HashMap(SEGMENT),默认是16个。 加锁是针对一个SEGMENT加锁，理想情况可以16个线程同时插入，大大提高吞吐量。

> 问题：系统需要全局锁时，消耗资源会更多。比如 HashMap.size()，这种会为每个SEGMENT加锁。

> 只在类似于size()获取全局信息的方法调用并不频繁时，这种减小粒度的方法才能提高系统吞吐量。

3. 读写分离锁来替换独占锁

> ReadWriteLock 读写锁

> 减少锁粒度是通过分割数据结构来实现优化，读写锁则是对系统功能点的分割。

4. 锁分离

> LinkedBlockingQueue. ArrayBlockingQueue.  使用两个锁，锁弱了锁竞争的可能性。

```
    public final ReentrantLock takeLock = new ReentrantLock();
    private final Condition notEmpty = takeLock.newConditioon();
    
    public final ReentrantLock putLock = new ReentrantLock();
    private final Condition notFull = putLock.newConditioon();
    
```

5. 锁粗化
```
    public void demoMethord() {
        synchronized(lock) {
            // do sth.
        }
        // do something. end quickly.
        synchronized(lock) {
            // do other thing.
        }
    }
    
修改为 ====>

    public void demoMethord() {
        synchronized(lock) {
            // do sth.
            // do something. end quickly.
            // do other thing.
        }
    }
```
```
    for (int i=0; i < CIRCLE; i++) {
        synchronized(lock) {
            //TODO
        }
    }
    
修改为 ====>
    synchronized(lock) {
        for (int i=0; i < CIRCLE; i++) {
            //TODO
        }
    }
```

### JVM对锁优化做的努力
1. 偏向锁

> 核心思想: 如果一个线程获得了锁，就是进入偏向模式。当这个线程再次请求锁时，无须再做任何同步操作。这样节省了大量有关锁申请的操作，提高性能。

> 对于几乎没有锁竞争的场合，连续多次极有可能是同一个线程请求相同的锁

> 对于锁竞争激烈的场合，不适用

> -XX: +UseBiasedLocking

2. 轻量级锁

> 简单的将对象头部作为指针，指向栈帧中的内部，来判断一个线程是否持有对象锁。

> 如果线程获取轻量级锁成功，则可以顺利进入临界区

> 如果失败, 膨胀为重量级锁，线程挂起

3. 自旋锁

> 自适应自旋锁

> 请求锁时无法获得，当前线程空循环一段，来等待锁的释放。

4. 锁消除

> 基于  “逃逸分析"

> 通过对运行上下文的扫描，去除不可能存在的共享资源竞争的锁。

### ThreadLocal

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
- ForkJoinPool:  JDK1.7 新并发框架，分治思想。 

> 提交ForkJoinTask.

> RecursiveAtion 和 RecursiveTask 一个有返回值，一个没有。

> 并不是每个 fork() 都会促成一个新线程被创建，而每个 join()也不是一定会造成线程被阻塞。不会因为fork越来越细而产生更多的线程。

> 算法的名字就叫做 work stealing 算法

> fork放入工作队列，线程完成自己的任务，去其他线程窃取其他任务；join也不是单独等待，而是边取窃取任务工作，边等待。

### 并发集合:

1. 线程安全的HashMap: 
- Collections.synchronizedMap(new HashMap()): 它使用委托，将自己所有Map相关功能委托给传入的HashMap实现，而自己负责线程安全。mutex加锁
- ConcurrentHashMap: 减少锁粒度，分为16个SEGMENT的 HashMap,分别加锁。

2. 线程安全的List:
- Collections.synchronizedList(new LinkedList<String>()
- ArrayList非线程安全， Vector 线程安全，但效率不高
- ConcurrentLinkedQueue: 高并发下性能最好的队列, 采用无锁，CAS操作. 使用了sun提供的 UNSAFE, native 到 C层， 再映射为 操作系统的CAS操作。
```
    boolean casItem(E cmp, E val) {
        return UNSAFE.compareAndSwapObject(this, itemOffset, cmp, val);
    }
```

3. CopyOnWriteArrayList: 高效读取
- 读取时不需加锁，写入也不会阻塞读操作
- List需要修改时，并不修改原内容，而是复制原List，在副本中修改。写完之后再由副本替换原数据。

4. BlockingQueue: 数据共享通道
- ArrayBlockingQueue
- LinkedBlockingQueue
- 通过 ReentrantLock, Condition notEmpty, Condition notFull来实现队列为空挂 起直到有入队；队列满挂起直到有出队

5. SkipList: 跳表，随机数据结构
- 可以用来快速查找的数据结构， 有序
- 类似平衡树，但平衡树在插入时需要全局调整，而跳表只是对局部进行操作
- 高并发下，平衡树需要全局加锁，而跳表只需局部加锁
- 同时维护了多个链表，最底层包含全数据。  
- 数据冗余，空间换时间。
- ConcurrentSkipListMap

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

1. 为每一个线程分配不同的对象，由应用层来保证。ThreadLocal 只起到了简单的容器作用。
```
private static ThreadLocal<SimpleDateFormat> tl = new ThreadLocal<>();

public void run() {
    if (tl.get() == null) {
        tl.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }
    Date t = tl.get().parse("2018-12-12 15:23:" + i % 60);
}
```

2. 保存在了本线程的map里边
```
public void set(T value) {
        Thread t = Thread.currentThread();
        ThreadLocalMap map = getMap(t);
        if (map != null)
            map.set(this, value);
        else
            createMap(t, value);
    }
```
3. 因为set到了线程上，而对于线程池来说，线程没有退出，这些线程私有变量也不会回收。这样会造成内存泄漏。 使用 ThreadLocal.remove()移除该变量可以避免。或者 tl = null强制GC回收。

### 无锁
1. 比较交换(CAS): 

> CAS需要你提供一个额外的期望值，如果变量不是你期望的那样，说明它已被别人修改，你就重新读取，再次尝试就好了

2. AtomicInteger: 无锁的线程安全整数
3. AtomicReference: 无锁的对象引用

> ABA 问题， CAS

4. AtomicStampedReference: 设置对象时，对象值以及时间戳都必须满足期望值，写入才会成功。 可以防止ABA问题。
5. 数组无锁: AtomicIntegerArray
6. 普通变量享有原子操作: AtomicIntegerFieldUpdater<T>

> 升级变量必须可见，因为用的是反射

> 类型要添加 volatile

> 不支持 static 变量

### 死锁
1. 概念:两个或者多个线程，相互占用对方需要的资源，而都不进行释放，导致彼此之间都相互等待对方释放资源，产生了无限等待的现象
2. 可以通过jps, jstack查看
```
Found one Java-level deadlock:
=============================
"Phi B":
  waiting to lock monitor 0x00007fc1ad0333f8 (object 0x00000007957a69e0, a java.lang.Object),
  which is held by "Phi A"
"Phi A":
  waiting to lock monitor 0x00007fc1ad033558 (object 0x00000007957a69f0, a java.lang.Object),
  which is held by "Phi B"

Java stack information for the threads listed above:
===================================================
"Phi B":
	at com.walle.concurrent.deadlock.DeadLock.run(DeadLock.java:47)
	- waiting to lock <0x00000007957a69e0> (a java.lang.Object)
	- locked <0x00000007957a69f0> (a java.lang.Object)
"Phi A":
	at com.walle.concurrent.deadlock.DeadLock.run(DeadLock.java:35)
	- waiting to lock <0x00000007957a69f0> (a java.lang.Object)
	- locked <0x00000007957a69e0> (a java.lang.Object)

Found 1 deadlock.
```

## 并发模式与算法
### 单例模式
```
public class StaticSingleton {
    private StaticSingleton() {
        System.out.println("StaticSingleton create.")
    }
    private static class SingletonHolder {
        private static StaticSingleton instance = new StaticSingleton();
    }
    public static StaticSingleton getInstance() {
        return SingletonHolder.instance;
    }
}
```
此种创建方法的几点好处:

- getInstance 没有 synchronized, 高并发环境下性能优越
- 延迟加载，只有在用到的时候，才初始化
- SingletonHolder是private, 所以非共享，不会有并发问题，而内部类，可以利用JVM类初始化机制来创建单例

### 不变模式

- 核心思想是，一个对象一旦被创建，则它的内部状态将永远不会发生改变。
- 对不变对象的多纯种操作，是不需要进行同步控制的
- 在java 中要注意4点
- -  去除setter 方法
- -  所有属性为私有，并用final标记
- -  确保没有子类可以重载修改它的形为
- -  有一个可创建完整对象的构造函数
- 像String ,Integer, Double, Long等元数据包装类，均为不变模式实现，所有实例方法不需要同步。
```
    public final class Product {
        private final String name;
        
        public Product(String name) {
            this.name = name;
        }
        
        public String getName() {
            return name;
        }
    }
```
### 生产者-消费者模式

1. 生产都-消费都模式很好的对生产者线程和消费者线程解耦，优化了系统整体结构。同时，由于缓冲区的作用，允许生产者线程和消费者线程存在执行上的性能差异，从一定程度上缓解了性能瓶颈对系统性能的影响。
2. 用BlockingQueue作为共享内存缓冲区，来实现生产者-消费者

- 采用阻塞方式来完成数据同步，性能不高，不支持高并发

3. 无锁的缓存框架: Disruptor

- 采用ring buffer 环行队列. 

- 队列大小要设置成 2的整数次方，如1024。 这样通过 sequence & (queueSize - 1) 就可以立即定位到该元素，而比 取余 % 快得多

- 性能可以比BlockingQueue快一个数量级
- 采用 CAS
- 提供了不同的等待策略来提高吞吐量。 BlockingWaitStrategy < SleepingWaitStrategy < YieldWaitStrategy < BusySpinWaitStrategy 低延时，高CPU占用
- 解决CPU Cache 伪共享, 以 Padding方式

    > CPU Cache 伪共享是由于同一个Cache里边存入多个变量，导致一个失效其他的也失效，但原本这些变量不该失效，这样使命中率降低。其中一个方法是把这个变量进行padding，使他充满一个Cache。方法不太优雅。

### Future模式

1. 每一次返回拿到的 Future Data只是一个 契约(*Compact*)，之后真正取数据是用这个契约得到真实数据。 如果数据仍然没有准备好，则阻塞。
2. JDK Future模式:
- 实现Callable<T>的 call() 用于构建真实数据
- FutureTask<T> 的构造函数，传入真实数据构建
- future task在线程池启动, 它相当于 契约
- 调用 future task.get() 得到真实数据

### 并行流水线
- 各线程各司其职，处理发送到自己队列中事情，构建并发流水线。

### 并行搜索

- 无序数组的搜索:  分治思想， 起CPU相同核数的线程去分别求搜索结果。

### 并行排序

//TODO

### 矩阵算法

//TODO

### 网络BIO, NIO， AIO

1. BIO: Blocking IO, 阻塞IO

- 服务器会为每个客户端连接启用一个线程
- 为了接受客户端连接，服务器还有一个派发线程
- 客户端缓慢处理速度使用服务器也花费长时间等待

2. NIO: New IO，非阻塞同步IO

- 关键组件:

> Channel通道，类似于流，一个channel可以和文件或Socket对应

> Buffer: 数据要包装成Buffer的形式才能和Channel交互

> Selector:  一个Selector可以管理多个SelectalbeChannel. 当SelectableChannel的数据准备好时，Selector就会接到通知，得到那些准备好的数据。

- 核心思想: 有数据到来再通知我，我来同步处理。但处理是在线程中同步进行的，即处理还是由我来做的。

3. AIO: Asyc IO, 非阻塞异步IO

- 核心思想: 有数据到来并且处理完再通知我，业务逻辑以回调形式交给系统，即处理是由系统来做的，非阻塞，并且异步。

```
server = AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(PORT));


server.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {

    @Override
    public void completed(AsynchronousSocketChannel result, Object attachment) {
    }

    @Override
    public void failed(Throwable exc, Object attachment) {
    }
});
```

## Java 8
### 函数式编程
1. 函数式编程的特点
- 将函数作为参数传递给另一个函数
- 函数可以作为另外一个函数的返回值
2. 函数式编程基础
- @FunctionalInterface 函数式接口，只定义了单一抽象方法的接口。
```
@FunctionalInterface
public static interface IntHandler {
    void handle(int i);
}
```
- 接口默认方法
```
public interface IHorse {
    void eat();
    default void run() {
        System.out.println("horse run");
    }
}
```
- lambda表达式
- 方法引用

> 静态方法引用: ClassName::methodName

> 实例上的实例方法引用: instanceReference::mehodName

> 超类上的实例方法引用: super::methodName

> 类型上的实例方法引用: ClassName::methodName

> 构造方法引用: Class::new

> 数组构造方法引用: TypeName[]::new

```
    users.stream().map(User::getName).forEach(System.out::println);
```
3. 流式API： users.stream() 替代 for in 循环

> 并行流

```
IntStream.range(1, 1000000).filter(PrimeUtils::isPrime).count();

//parallel
IntStream.range(1, 1000000).parallel().filter(PrimeUtils::isPrime).count();
```

- 并行排序: Arrays.parallelSort(arr);

4. CompletableFuture: 增强的Future

> 通过CompletionStage提供的接口，可以在一个执行结果上进行多次流式调用。

```
    stage.thenApply(x -> squar(x))
         .thenAccept(x-> System.out.print(x))
         .thenRun(System.out.println());
```
5. StampedLock

> 读写锁的改进版本, 除了读读完全并发外，读写使用乐观锁

- 读时，会先long stamp = sl.tryOptimisticRead()
- 真正用的时候再验证stamp, sl.validate(stamp)
- 若一致，说明中间没有发生写操作，则原读取数据可以用
- 若不一致，说明中间发生写操作，会升级锁为 悲观锁，重新读取数据。

6. LongAdder:
- 采用和ConcurrentHashMap相同的思路，分离热点数据，将核心数据分离成多个cell.每个cell独立维护内部值。 当前对象的实际值由所有cell累计合成。
- LongAccumlator


## Akka
### 概念
- Actor并发模型，粒度比线程更小，可以启用极大量的Actor.
- 容错机制，允许在出现异常时进行恢复或重置
- 单机和网络都可进行高并发程序

### 消息
- 是Akka中的另一个核心组件，建议采用不变模式。可变的消息无法高效的使用高并发模式
- 消息投递的三种策略: **(消息可靠性由业务层保证)**

> 1. 至多一次投递，有可能造成消息丢失
> 2. 至少一次投递，有可能造成消息重复
> 3. 精确投递，既不丢失，也不重复

- Akka可以在一定程度上保证消息投递的顺序性

### Actor的生命周期
- 空 -- (actorOf) --> Actor
- Actor -- (resume) --> Actor
- Actor -- (stop, posionPill) --> 空
- Actor -- (restart) --> 新实例
- 新实例 -- (postRestart 替换) --> 新Actor

> 一些回调 preStart(), postStop(), preRestart()

### Actor的监督策略
- 一般由父Actor来监督，有2种策略:
- 1. OneForOneStrategy: 出问题时只重启被监管的Actor.
- 2. AllForOneStrategy: 出问题时父Actor会对其和兄弟Actor一起重启

### 选择Actor
- 批处理管理和通信: ActorSelection.
- 可利用通配符进行消息发送，如 "/user/worker_*"

### 消息收件箱inbox
```
ActorRef worker = system.actorOf(Props.create(MyWorker.class), "worker");

final Inbox inbox = Inbox.create(system);
inbox.watch(worker);
inbox.send(worker, Msg.WORKING);
inbox.send(worker, Msg.DONE);
inbox.send(worker, Msg.CLOSE);

while (true) {
    Object msg = inbox.receive(Duration.create(1, TimeUnit.SECONDS));
    if (msg == Msg.CLOSE) {
        System.out.println("My worker is closing.");
    } else if (msg instanceof Terminated) {
        System.out.println("My worker is dead.");
        system.shutdown();
        break;
    } else {
        System.out.println(msg);
    }
}
```

### 消息路由 Router
- 实现 一组Actor的负载均衡。
- 一个Router可以接管一组Actor,发往Router的消息会以一种策略转发给Actor中的一个或多个，如RR策略.
- 出问题或被关闭的Actor可以从Router中踢出。
```
public Router router;
{
    List<Routee> routees = new ArrayList<>();
    for (int i =0;i < 5 ; i++) {
        ActorRef worker = getContext().actorOf(Props.create(MyWorker.class), "worker_" + i);
        getContext().watch(worker);
        routees.add(new ActorRefRoutee(worker));
    }
    router = new Router(new RoundRobinRoutingLogic(), routees);
}

@Override
public void onReceive(Object message) throws Throwable {
    if (message instanceof MyWorker.Msg) {
        router.route(message, getSender());
    }
}
```

### Actor的内置状态转换
- 根据当前Actor的不同状态，来响应到来的Message.
- getContext().become 来切换状态，在apply()实现不同状态对Message的处理。
```
    Procedure<Object> angry = new Procedure<Object>() {
        @Override
        public void apply(Object param) throws Exception {
            System.out.println("Angry apply: " + param);
            if (param == Msg.SLEEP) {
                getSender().tell("I'm already angry.", getSelf());
                System.out.println("I am already angry.");
            } else if (param == Msg.PLAY) {
                System.out.println("I like playing.");
                getContext().become(happy);
            }
        }
    };
```

### Actor中的Future模式
- Ask 之后得到 契约，之后两种处理
- 一种是异步调用转为同步阻塞调用， Await等待Actor返回
- 另一种是管道方式，pipe()，等返回后直接转到另一个处理Actor.
```
Future<Object> f = ask(worker, 5, 1500);
int re = (int) Await.result(f, Duration.create(6, TimeUnit.SECONDS));

System.out.println("return:" + re);

f = ask(worker, 6, 1500);
pipe(f, system.dispatcher()).to(printer);
```

### Actor修改共享数据
- Agent: 一个Agent提供了一个对变量的异步更新

> 当对变量进行更新，会向Agent下发一个action.

> 多个action会在ExecutionContext中被并发调度执行

> 任意时刻只能执行一个action.

> Agent的修改可以使用 send()或者alter().

```
public static  Agent<Integer> counterAgent = Agent.create(0, ExecutionContexts.global());
```

### STM 软件事务内存:
- 具有 ACI事务属性，不具有D，持久化
- 事务由 Coordinated message实现,出错情况可以进行回滚
```
    private Ref.View<Integer> count = STM.newRef(100);
    
    final Coordinated c = (Coordinated) message;
    c.atomic(new Runnable() {
        @Override
        public void run() {
            if (count.get() < downCount) {
                throw new RuntimeException("less than " + downCount);
            }
            STM.increment(count, -downCount);
        }
    });
```



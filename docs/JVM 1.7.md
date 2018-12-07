## 深入理解java 虚拟机

### JVM 运行时数据区域
1. 程序计数器：Program Counter Pointer
* 当前线程所执行的字节码的行号指示器
* 线程私有
* 如果正在执行的是java代码，记录的是正在执行的虚拟机字节码指令的地址
* 如果正在执行的是Native方法，则为空
* 无OOM 情况
2. JVM 栈：
* 线程私有
* 每个方法执行时，会创建一个栈帧（Stack Frame)，存储局部变量、操作数栈、动态链接、方法出口等。
* 局部变量表存放了基本数据类型、对象引用reference和returnAddress.
* OOM  StackOverflowError
3. 本地方法栈
4. Java堆
* 各线程共享
* 所有的对象以及数组要分配在堆上（JIT，不是绝对）
* 从内存回收的角度看，堆分为 新生代和老年代，新生代又分为 Eden, From Survivor, To Survivor.
* 从内存分配的角度看，堆可划分出 线程私有的分配缓冲区(Thread Local Allocation Buffer. TLAB)
5. 方法区
* 各线程共享
* 存储 已加载的类信息、常量、静态变量、JIT编译器的代码
* None-Heap
* 只有HotSpot 也称为 永久代 (Permanent Generation)，方便和堆区使用相同GC
* 这个区域的GC，主要是 常量池的回收和 类卸载

X. 直接内存
* 并不是运行时数据区域的一部分
* NIO, 基于Channel和Buffer的I/O方式，可以使用Native函数库直接分配堆外内存，通过在Java堆中的DirectByteBuffer对象作为这块内存的引用。
* 不会受到堆内存大小限制，但会受到本机总内存大小限制

### Java对象的创建
1. 先检查是否能在常量池中定位到一个类的符号引用，且该类是否加载,若没有则先ClassLoad
2. 堆中分配内存
* 内存规整：指针碰撞 Bump the Pointer
* 内存不规整：空闲列表 Free List
* 是否规整，由 垃圾收集器是否带有压缩整理而定。
* Serial&ParNew(Compact) --> Bump the Pointer
* CMS(Mark-Sweep) --> Free List.
* 需考虑线程安全：1 内存分配进行同步处理(CAS)，2 TLAB (在该线程的TLAB上分，不够用再同步锁)
3. 初始化内存为 0
4. JVM对 对象必要设置 ， 设置在 对象头(Object Header)
* 哪个类的实例
* 如果找到类的 meta data
* 对象 hash code
* GC 分代年龄
5. 对象初始化 <init>

### 对象的内存布局
1. 对象头 Header
* 一部分称为 Mark Word. 存储运行时数据，如HashCode、GC分代年龄、锁状态等
* 非固定数据结构，有标志位确定存储类型(01 未锁定，00 轻量级锁定， 10 重量级锁定，11 GC标记)
* 另一部分， 类型指针，即对象指向它的Class meta data.
* 如果对象是数组，还必须包含 数组长度
2. 实例 Instance Data
* 在程序代码中定义的各种类型的字段内容
* 父类继承、子类定义的， 都需要记录。
3. 对齐填充 padding
* HotSpot中，对象大小必须是8字节的倍数，没有对齐需要补全

### 对象的访问定位
1. reference(stack) --> handler(handler pool, heap, 2 pointer) -->   1-p-instance(heep) & 2-p-Class(method area)
2. reference(stack) --> instance(heap) --> Class (method area)

## 垃圾回收和分配策略

### 对象存活判定算法
1. 引用计数算法
* 有一个地方引用，则计数器+1，引用失效时就-1.
* 缺陷： 很难解决对象之间的相互循环引用
2. 可达性分析算法 *Reachability Analysis*
* 通过一系列的 GC Roots为起点，向下搜索，当一个对象到GC root没有任何引用链相边（即图论中的不可达），则证明此对象是不可用的。
* GC Roots 包括以下几种：
  > JVM Stack 引用对象

  > Methord Area中 类静态属性引用的对象

  > Methord Area中 常量引用的对象

  > 本地方法栈中Jni引用的对象

### 引用的分类
1. 强引用：
```
> Object ob = new Object()
```
只要强引用还在，GC永远不会回收掉被引用的对象。
2. 软引用：一些还有用但并非必需的对象。  当系统要发生OOM之前，会对软对象进行二次回收，如果还是不够，则报OOM.
3. 弱引用：下一次GC,无论内存是否足够，都会回收掉引用对应的对象。
4. 虚引用：无法通过一个虚引用取得对象实例，只是当对象被回收时，会收到一个系统通知。

### 是否回收
1. 不可达算法标记后，并不是 “非死不可”。 至少要两次标记，才会死亡。
2. 第一次标记后，会放入F-Queue中。
3. 对F-Queue中对象进行二次标记，如此时之前有用到该对象，则逃脱。
4. 二次标记的对象，会被回收。

### 方法区GC
* 永久代回收主要回收：废弃常量 和 无用的类（类卸载）
* 无用的类  的判断：
> 该类所有实例都已回收

> 加载该类的ClassLoader已被回收

> 该类对应的Class对象，没有任何地方被引用

### GC 算法
1. 标记-清除算法 (**Mark-Sweep**):
* 有2个问题（效率不高，空间问题（产生大量内存碎片））
2. 复制算法 (**Copying**): 
* 内存平分为二，一块内存满了，把活着的对象复制到另一块，该块内存一次性清除，高效且没有内存碎片。 问题是：内存缩小到了原来的一半。

> 新生代GC一般采用Copying，内存并非1：1，而是分一块较大的Eden Space 和两块较小的Survivor Space. 每次只使用Eden + 1块Survivor.

> GC时，将 Eden + Survivor1 ----> Surivor2, 再清空 Eden + Survivor1.

> HotSpot JVM默认 Eden : Survivor = 8:1，即有效内存= 总内存 * 90%，只浪费了10%

> 极端情况： Eden + Survivor1 > Survivor2, 即 存活对象在Survivor2中装不下，需要进行 分配担保(Handle Promotion), 由老年代担保

3. 标记-整理算法(**Mark-Compact**)：
* Copying算法不适合老年代
* 标记-整理 类似 标记-清除，但不是简单清除，而是存活对象向一边移动，最后清理边界以外对象。

4. 分代收集算法(**Generational Collection**)：
* New Generation --->  Copying
* Old Generation ---> Mark-Sweep | Mark-Compact
* 

### HotSpot 枚举根节点 GC Roots
1. GC Roots一般为 全局引用和执行上下文(局部变量引用)
2. 该过程会出生卡顿，Sun称之为 Stop the World. CMS中也不可避免
3. OopMap的数据结构来记录对象引用。类加载完成后，HotSpot就知道对象什么偏移量存有什么类型。
4. Safepoint. 线程只有在到达安全点时才能暂停。

> 抢先式中断：首先线程全部中断，让不在安全点上的线程恢复，并跑到安全点。几乎没有虚拟机采用此方法

> 主动式中断：设置一个标志，线程执行时主动轮询这个标志，若发现为true，则中断挂起。 轮询标志的地方和安全点重合。
5. Safe Region.

### 垃圾收集器
1.  Serial 收集器

> 单线程执行， 在新生代操作，使用复制算法，GC时必须暂停其他工作线程,Stop the world.

> JVM运行在Client模式下，新生代的默认收集器

2. ParNew收集器

> Serial 收集器的 多线程版本

> 许多JVM运行在 Server模式下，首选的新生代收集器

> 它可与 CMS老年代收集器 搭配使用

3. Parallel Scavenge 

> 多线程、新生代收集器

> 关注点不是 缩短GC STW时间，而是 达到一个可控的 吞吐量， 吞吐量=运行用户代码时间/CPU总消耗时间

> 自知应调节策略：内存管理的调优任务交由 JVM自己来完成

4. Serial Old:

> 单线程执行，老年代， 使用 标记整理 算法

> JVM运行在Client模式下，老年代的默认收集器

5. Parellel Old:

> 多线程， 老年代， 使用标记整理 算法

6. CMS (Concurrent Mark Sweep)收集器：

> 以获取最短回收停顿时间为目标的收集器， 老年代，多线程，可并发。 可与 Serial or ParNew 配合使用， 如果fail，切回 Serial Old执行

> 首个垃圾回收线程 与用户线程同时进行的 收集器

> 运行过程分为4步：
*  初始标记:  STW, 标记GC Roots能直接关联到的对象， 速度快
*  并发标记： GC Roots Tracing
*  重新标记:  STW, 补差（并发标记中导致标记变动的那一部分）
*  并发清除

> 无法处理 浮动垃圾， CMS在并发清理时用户线程还会产生垃圾，称为Floating Garbage.  只能在下次清除

> CMS不能等到 老年代满了之后再清理，而是阀值到达92%（JDK1.6）就启动GC.

> Mark-Sweep，内存碎片，导致大对象放不下，从而更多的 Full GC.

7. G1 (Garbage-First)收集器

> 特点：
* 并行与并发，充分利用多CPU 多核的硬件优势
* 分代收集
* 空间整理: Mark-Compact, Copying.
* 可预测的停顿

> Region: 将整个堆划分为多个大小相等的Region
* 避免全局垃圾回收， 根据回收价值，维护一个回收优先列表，每次根据允许收集时间，回收价值最大的Region.

> 运行过程分为4步：
*  初始标记:  STW, 标记GC Roots能直接关联到的对象， 速度快
*  并发标记： GC Roots Tracing
*  最终标记:  STW, 补差（并发标记中导致标记变动的那一部分）
*  筛选回收


### 内存分配 与 回收策略
1. 对象优先分配在Eden, Eden没有足够空间，则发起一次 Minor GC
2. 大对象直接进入老年代
* 典型的大对象是 很长的字符串 和 数组
* 多大进入老年代，可通过 -XX: PretenureSizeThreshold设置
3. 长期存活对象将进入老年代
* 对象年龄Age
* 对象在Eden出生并经过一次Minor GC还存活，并且Survivor容纳下它的话，将被移动到Survivor空间，且Age为1
* 在Survivor每熬过一次MinorGC, Age +1. 到达一定Age(default 15),晋升老年代
* 晋升阀值设置 -XX: MaxTenuringThreshold
4. 动态对象年龄判定
* 如果在Survivor空间中相同Age所有对象大小的总和， 大于Survivor空间的一半，>= Age 对象进入 老年代
5. 空间分配担保
* 发生Minor GC前，JVM会先检查老年代最大可用的连接空间是否大于新生代所有总空间
* 若成立，Minor GC是安全的
* 若不成立，JVM查看 HandlePromotionFailure是否允许担保失败，
* 如果允许，继续检查老年代最大可用连续空间是否大于历次晋升到老年代对象的平均大小，
* 若大于，将尝试Minor GC;
* 若小于，则进行Full GC.


## JDK 调试工具：
* jps: 列出 LVMID(Local Virtual Machine Identifier)
> -m: 列出启动main()时使用的 参数
> -v: 列出运行时 JVM的参数
* jstat: 用于监视JVM各种运行状态信息的工具. 它可以显示进程中的类装载、内存、垃圾回收、JIT编译等运行数据
> jstat -gc 2764 250 20 每250ms查询一次进程2764的GC情况，共查20次

> -class 类装载、卸载情况，以及总空间 和 耗时

> -gc 垃圾回收相关，如 -gcnew -gcold -gcutil

> -compiler JIT编译器编译过的方法和耗时信息

* jinfo: 实时查看和调整JVM的各项参数

> -flag k=v 修改运行期的参数

* jmap 生成堆转储快照(一般称为heapdump文件)

> -dump:  jmap -dump format=b,file=xxx.bin 3500

* jhat: 用来分析jmap生成的堆转储快照
* jstack: 用于生成JVM当前时刻的线程快照(threaddump文件)
* 可视化工具： JConsole, VitualVM, JProfile.
* BTrace: 可以在不停止目标程序的前提下，通过HotSpot JVM的 HotSwap技术 动态加入原本并不存在的调试代码。

## Class类文件结构
* 实现语言无关性的基础是  虚拟机和字节码存储格式
* Class文件格式采用伪结构来存储，只有两种数据类型：无符号数 和表。

类型 | 名称 | 数量|
---|---|---|
u4 | magic | 1|
u2 | minor_version | 1|
u2 | major_version | 1|
u2 | constant_pool_count | 1|
cp_info | constant_pool | constant_pool_count -1|
u2 |access_flags | 1|
u2 | this_class | 1|
u2 | super_class | 1|
u2 | interfaces_count | 1|
u2 | interfaces | interfaces_count|
u2 | field_count | 1|
fields_info | fields | field_count|
u2 | methods_count | 1|
methods_info | methods | methods_count|
u2 | attributes_count | 1|
attributes_info | attributes | attributes_count|

* 魔数 0xCAFEBABE

* 高版本的JDK可以向下兼容，但不能运行之后的Class文件，比如51版本可以执行49的Class文件，但不能执行52的

* constant pool 是Class文件中的 资源仓库, 由 字面量 和符号引用 组成。
---

在Javac编译成 ByteCode时，因为没有连接，所以不知道最终在内存中的具体地址。而当运行期或类加载时，通过常量池中的符号引用，得知它真正的内存地址后，再对这些index做替换。比如this_class，通过常量池找到全量限定名com.walle.Test.java，Test做类加载后，有实际内存地址，把this_class的index 指向修改为此地址

---

> javap -verbose Test.class 可以反编译得到 具体constant pool信息

* Access Flags. 包括:

> 是Class还是Interface.

> 是否定义为 Public

> 是否定义为 Abstract

> 是否被声明为 final

* 类索引 、 父类索引 、 接口索引: 描述了 这个类的继承关系，均指向contant pool. Java只支持单继承，所以继承最多有一个，最少有一个Object. 而 接口可以有很多，所以第一位记录数量
* 字段表: 用于描述接口或者类中声明的变量，字段包括类级的变量以及实例级变量，但不包括方法中的局部变量。
> access_flag, name_index, descriptor_index, attributes.

* 方法表：
> access_flag, name_index, descriptor_index, attributes.

> 方法中的Code在哪里？ 在属性表中的一个 "Code" 的属性表里

* 属性表： 在 类、字段表、方法表中都会存在

> Code属性

> 1. max_stack:  操作栈最大深度
> 2. max_locals: 局部变量所需的空间
> 3. code_length & code: 存储Bytecode 指令
> 4. exception_table: 异常表 对bytecode执行的描述，from行--to行做异常处理,对应 try catch.

> Exception属性: 异常列表，有哪些异常 IllegalParameterException ,NullPointerException

> LineNumberTable属性: bytecode对应 Test.java中的哪一行，调试用
> LocalVairiableTable 属性: 局部变量参数，用于调试时显示 变量名称等信息。
> SourceFile属性: 记录生成这个Class文件的源码文件
> ConstantValue属性: 初始化变量，比如 static int x = 3, 3存入ConstantValue中。
> InnerClasses属性: 描述内部类与宿主类之间的关联。
> Deprecated属性: 废弃，与 @deprecated对应
> Synthetic属性: 不是由Java源码产生的，而是编译器生成的
> StackMap属性: 代替类型推导验证器, 字节码是否符合逻辑约束
> Signature属性: Java 泛性采用擦除法实现的伪泛性，为了在Java反射API能够获取泛性

## 字节码指令
* 加载和存储指令：操作数栈<-->局部变量表
* 运算指令: iadd, isub, idiv, iand, ior,
* 类型转换指令: 宽化(int->long), 无需转化; 窄化(long -> int), l2i, f2i
* 对象创建与访问指令: new , newarray, getfield, putfield. iaload
* 操作数栈 管理指令: pop, dup, swap
* 控制转移指令: ifeq, iflt, tableswitch, goto
* 方法调用指令: invokevirtual(实例方法调用), invokestatic(类方法调用), invokedynamic(动态解析出调用限定符所引用的方法，多态)
* 同步指令: Java支持 方法级的同步 和 方法内部 一段指令的同步。都是使用管程(Monitor)来支持的
> 方法级的同步，通过 Access Flag(ACC_SYNCHRONIZED)

> 同步一段指令, Java 语言中的synchronized 块来表示，在字节码指令中monitorenter , monitorexit

## 虚拟机类加载机制：
* JVM把Class文件加载到内存，并对数据进行校验，转换解析和初始化，最终形成可以被JVM直接使用的Java类型，这就是 JVM  ClassLoad机制。
* Class的生命期: 
> Loading-> Verification -> Preparation -> Resolution -> Initialization -> using -> Unloading.   
> Verification -> Preparation -> Resolution 又叫 linking 连接，与C语言对应

* 加载(Loading): 
> 1.  通过一个类的全限定名获取定义此类的Bytecode. 
> 2.  将bytecode所代表的静态存储结构转化为方法区的运行时数据
> 3.  在内存中生成一个代表这个类的 java.lang.Class 对象，作为方法区这个类的各种数据的访问入口(Hotspot JVM中Class对象是存放在方法区中，而不是Java堆中)

* 验证(Verification)
> 文件格式验证: magic number, minor&major version, constant pool, CONSTANT_Utf8_info 常量中是否有不符合UTF-8的编码

> 元数据验证: 语义分析，保证其描述符合Java语言规范的要求. 比如，父类是否继承了不允许被继承的类，是否实现了接口中要求实现的方法等

> 字节码验证: 
> 符号引用验证: 

* 准备: 正式为类变量分配内存并设置类变量初始值的阶段
```
public static int value = 123;
```

* 解析： JVM将常量池内的符号引用替换为直接引用的过程。
* 初始化：执行类构造器<clinit>()方法的过程
> <clinit>() 方法是由 编译器自动收集类中的所有类变量的赋值动作和静态语句块(static{}块)中的语句合并产生的。


## 类加载器 ClassLoader
* 类加载器分类:

>  启动类加载器(Bootstrap ClassLoader): 是虚拟机的一部分，用C++实现，它将<JAVA_HOME>\lib中的虚拟机识别的类库加载到虚拟机中。

> 扩展类加载器(Extension ClassLoader): 负责加载<JAVA_HOME>\lib\ext目录下的所有类库

> 应用程序类加载器(Application ClassLoader):  getSystemClassLoader()，所以也叫做 系统类加载器，它负责加载用户类路径上ClassPath上所指定的类库。 它是程序中默认的类加载器。

* 双亲委派模型(Parent Delegation Model): 各个类加载器的层次关系
> 自定义类加载器 -> 应用程序类加载器 -> 扩展类加载器 -> 启动类加载器

> 工作过程: 如果一个类加载器收到了加载请求，它首先不会自己去尝试加载这个类，而是把这个请求委派给父类加载器去完成。因此所有的加载请求最终都是传送到顶层的启动类加载器中。 只有父加载器无法完成时，子加载器才会尝试自己去加载。

> 好处: 有优先级，保证了像java.lang.Object这样的类，只会来自 rt.jar，保证在各个类加载环境是同一个，而不能是用户自己创建一个 java.lang.Object，并通过应用类加载器加载进来，其中有些方法实现不同。

* 破坏双亲委派模型:
> JDBC, JNDI是Java 标准服务，它由bootstrap classLoader去加载，但jdbc就是对资源进行集中管理，它需要调用由独立厂商实现并部署在ClassPath下的Jdbc接口提供者的代码
> 利用Thread Context ClassLoader 完成，可以通过 Thread的 setContextClassLoader()设置
> Jdbc通过 父类加载器请求子类加载器去完成类加载动作，实际是对双亲委派模型的破坏。
> 代码热替换(HotSwap), 热部署(HotDeployment),  OSGi

## 字节码 的执行引擎
* 物理机 vs 虚拟机
> 物理机的执行引擎是建立在 CPU， 硬件， 指令集 和 操作系统上的。
> 虚拟机自行实现，如 操作栈，字节码指令, 局部变量。 也可以通过JIT直接编译成本地代码执行

* 栈帧(Stack Frame):

> 是用于支持虚拟机进行方法调用和方法执行的数据结构，

> 它存储了方法的局部变量表、操作数栈、动态连接和方法返回地址

> 每一个方法的调用，都对应着 一个栈帧在虚拟机栈中的入栈和出栈的过程

* 局部变量表(Local Variable Table): 

> Variable Slot: 存放一个boolean, byte,char, int, reference.

> Code 中的 max_locals确定了局部变量表的最大容量。

> 虚拟机是使用局部变量表完成参数值到参数变量列表的传递过程的

* 操作数栈(Operand Stack)

> LIFO (Last In First Out)栈

>  Code 的  max_stacks定义了操作数栈的最大深度

* 动态连接:

> 每个栈帧都包含一个指向运行时常量池中该栈帧所属方法的引用，为了支持方法调用过程中的动态连接(Dynamic Linking)

* 方法返回地址:  上层方法的PC地址(正常返回)

> 方法退出的过程实际上就等同于把 当前栈帧出栈，恢复上层方法的局部变量表和操作数栈，把返回值压入调用者栈帧的操作数栈中，调整PC计数器的值以指向方法调用者指令后面的一条指令。

### 方法调用:
* 方法调用 vs 方法执行: 

> 方法调用只是确定要调用方法的哪个版本，如多态时。

> Class文件中只是符号引用，而不是直接引用。  需要在 类加载时，甚至 运行时才能确定调用 方法的哪个版本。

> java动态扩展能力。 

* 解析(Resolution): 

> 在类加载过程中将符号引用转换为  直接引用

> 前提是 方法在运行之前就能确定使用哪个版本。

> "编译时可知，运行时不可变"，符合这两点的方法有 静态方法 和 私有方法。字节码指令对应 invokestatic, invokespecial， 非虚方法。 还有一类是 声明为 final 的方法，因为其不可变

* 分派(Dispatch):

> 运行期才能确定使用哪个版本

> 对应 invokevirtual, invokedynamic

* 动态类型语言:

> 动态语言的关键特征是，它的类型检查的主体是在运行期，而不是在编译期。

> 变量无类型，而变量值才有类型

### 解释执行
* Java:  程序源码 -> 词法分析 -> 单词流 -> 语法分析 -> 抽象语法树 -> 指令流 -> 解释器 -> 解释执行
* C/C++:  程序源码 -> 词法分析 -> 单词流 -> 语法分析 -> 抽象语法树 -> 优化器 -> 中间代码 -> 生成器 -> 目标代码
* Java 程序的编译是 半独立的实现(生成中间字节码)

## 编译期优化--早期优化

### Javac编译器
* 解析与填充符号表

> 词法分析--> Token

> 语法分析 --> 抽象语法树(AST, Abastract Syntax Tree)

> 填充符号表 --> To Do List 

* 注解处理器 操作AST, 有注解，就重新 词法分析--> 语法分析 --> 填充符号表
* 
* 语义分析和字节码生成

> AST能表示一个结构正确的源程序抽象，但无法保证逻辑正确

> 语义分析主要任务是 对结构上正确的源程序进行上下文有关性质的审查,如类型检查。 前面 boolean b=false; 后边就不能  b=3的赋值

> 标注检查-- 常量折叠, 如下， 后续a只是3，而不会再代入 2 + 1;
```
 int a= 1 + 2;
 int c = a + 1;
```
> 数据及控制流分析:  比如局部变量是否同仁，方法每条路径都有返回值等

> 解语法糖: 泛型、变长参数、自动装箱/拆箱等，虚拟机在运行时并不支持这些语法，在编译器要还原回简单的语法结构。

> 字节码生成: 还进行了少量的代码添加工作，如实例构造器<init>(), 类构造器 <clinit>()

### Java 语法糖
* 泛型 --> 类型膨胀(C#) vs 类型擦除(Java)
```
 源代码 --->
 
 Map<String,String> hashMap = new HashMap<String,String>();  
 
 javac , javap 之后--->
 
 Map hashMap = new HashMap(); 
```
* 自动装箱、拆箱
```
 源代码 --->
 
 Integer a = 3;  
 
 javac , javap 之后--->
 
 Integer a = Integer.valueof(3);  
 拆箱是 Integer.intValue()
```
* 遍历
```
 源代码 --->
 
 for (int i : list)  
 
 javac , javap 之后--->
 
 for (Iterator localIteratior = list.iterator();localIterator.hasNext();)
```
* 变长参数  String... items   ->  String[] items

## 运行期优化 -- 晚期优化
* Hotspot JVM的编译器:

> 解释器(Interpreter)  ---- 即时编译 --> 编译器 (JIT Client Compiler-C1 & Server Copiler-C2)

> 编译器 ----> 逆优化 --> 解释器

> Mix Mode, Interpreted Mode, Compiled Mode.

> -Xint 强制虚拟机使用 解释器, -Xcomp 强制虚拟机使用 编译器，但只是优先使用编译器， 解释器在编译器无法执行时介入

* 热点代码: 
1. 被多次调用的方法
2. 被多次执行的循环体

* 热点探测(Hot Spot Detection)
1. 基于采样的热点探测
2. 基于计数器的热点探测 

* HotSpot JVM采用 计数器热点探测，准备了两类计数器

1. 方法调用计数器: Client模式下 1500次，Server 模式下 10000次，通过 -XX: CompileThreshold来设定

> 方法调用时，先查看是否有JIT编译过的版本，如果存在就使用；如果不存在，使方法调用计数器 + 1, 再判断是否超过 Threshold，若超过则提交一个编译请求到 编译器。 本身还按解释器执行，直到编译器完成，替换该方法调用。

> 方法调用次数并不是 绝对值，而是一段时间内的阀值。当超过该时间，则方法调用次数减半。--- 称为 计数器热度的衰减(Conter Decay).  这段时间称为统计的 半衰周期(Counter Half Life time).   -XX: -UserCounterDecay 启/停  -XX: CounterHalfLifeTime

2. 回边计数器

> 统计循环体执行次数， Bytecode中将控制流向后跳转叫作 回边 Back Edge

> -XX: OnStackReplacePercentage 来调整回边计数的阀值。

> 提交 OSR编译

> 回边计数器统计的是 绝对值， 没有衰减。

### 编译优化技术
```
 Class B {
     int value;
     final int get(){
         return value;
     }
 }
 
 public void foo() {
     y = b.get();
     // do stuff
     z = b.get();
     sum = y + z;
 }
```
1. 方法内联(Method Inlining):
一是去除方法调用的成本(建立栈帧等)，二是为其他优化建立良好的基础
```
 public void foo() {
     y = b.value;
     // do stuff
     z = b.value;
     sum = y + z;
 }
```
2. 冗余存储消除(Redundant Loads Elimation)
```
 public void foo() {
     y = b.value;
     // do stuff
     z = y;
     sum = y + z;
 }
```
3. 复写传播(Copy Propagation)
```
 public void foo() {
     y = b.value;
     // do stuff
     y = y;
     sum = y + y;
 }
```
4. 无用代码消除(Dead Code Elimination)
```
 public void foo() {
     y = b.value;
     // do stuff,
     sum = y + y;
 }
```
### 经典优化技术
1. 公共子表达式消除(Common Subexpression Elimination)
```
 int d = (c * b) * 12 + a + (a + b * c)    (公共子表达式消除， c * b 不用算多次)
 
 ==> int d = E * 12 + a + a + E  (代数化简)
 ==> int d = E * 13 + a * 2   
```
2. 数组边界检查消除(Array Bounds Checking Elimination)

> 编译器只要通过数据流分析就可以判定循环亦是的取值范围永远在区间[0, foo.length)之内， 那整个循环中就可以把数组的上下界检查消除。

3. 自动装箱消除(Autobox Elimination)   //TODO
4. 安全点消除(Safepoint Elimination)   //TODO
5. 消除反射(Dereflection)   //TODO
6. 方法内联

> 把目标方法的代码"复制"到发起调用的方法之中，避免发生真实的方法调用 

> 但由于实例方法是虚方法，在编译器无法确定方法的版本，只能在运行期确定。

> 类型继承关系分析技术(Class Hierachy Analysis, CHA), 遇到虚方法后，查看CHA已加载的类关系，若方法只有一个版本，就可以进行内联。但属于"激进优化"，要留一个"逃生门"(Guard条件不成立时的Slow Path),称为 "守护内联"。若之后加载的类继承关系有变化，要退回解释状态或重新编译。

7. 逃逸分析(Escape Analysis)
* 分析对象动态作用域，例如对象生成后，传递到其他方法，叫方法逃逸，被其他线程使用，叫线程逃逸。
* 若分析出对象不会逃逸到方法或线程之外，可以做一些高效优化

> 栈上分配: 在栈帧上分配对象，对象内存空间会在栈帧出栈时销毁，这样GC会更省力

> 同步消除: 无线程逃逸，则无读写竞争，同步措施可消除

> 标量替换: 不创建对象，而是创建标量,如int, char等。

## Java 内存模型
1. 主内存 、 工作内存

> JMM 规定所有变量都存储在主内存中

> 每条纯种还有自己的工作内存，线程的工作内存中保存了变量的 主内存副本拷贝，线程对变量的所有操作都在必须在工作内存中进行

> 不同线程之间无法直接访问对方工作内存中的变量，线程间变量值的传递均需通过主内存来完成

2. 内存间交互操作： 主内存 <--> 工作内存

> lock , unlock,  read, load, use, assign, store, write

3. volatile  线程不安全的
* 保证此变量对所有线程的可见性， 一个线程修改了该变量，新值对其他线程是立即可知的。
* 禁止指令重排序优化-

### Java 线程
1. Java线程映射到一条轻量级进程中，与内核线程1：1
2. 线程调度：协同式调度  ， 抢占式调度(Java采用)
3. 线程优先级
4. 线程转换

> New

> Waiting: 以下方法会进入无限期等待
- 没有设置Timeout的Object.wait()，
- 没有设置Timeout的Thread.join(),
- LockSupport.park()

> Timed Waiting:以下方法会进入限期等待
- Thread.sleep()
- 设置Timeout的Object.wait()，
- 设置Timeout的Thread.join(),
- LockSupport.parkNanos()
- LockSupport.parkUtil()

> Blocked: 在程序进入同步区域时，进入这种状态。 
- “阻塞状态”和“等待状态”的区别是：“阻塞状态”在等待着获取到一个排他锁，这个事件将在另外一个线程放弃这个锁时发生；“等待状态”则是在等待一段时间，或者唤醒动作的发生

> Terminated

## 线程安全
### 线程安全的实现方法
1. 互斥同步（阻塞同步， 悲观并发策略）

> 保证共享数据在同一个时刻只个线程使用，这其中有 临界区(Critical Section)、互斥量(Mutex) 、 信号量(Semaphore)

> Java中互斥同步手段就是 synchronized, 编译后会在同步块的前后形成monitorenter, monitorexit两个字节码指令，这两个指令都需要一个 reference参数。 若Java中明确指定了对象参数，reference就这个对象;若没有指定，根据synchronized修饰的是实例方法还是类方法，去取对应的对象实例或Class对象作为锁对象

> synchronized块是可重入的，并且在已进行的线程执行完前， 会阻塞其他线程进入。 

> Java线程是映射到OS原生线程之上，阻塞和唤醒一个线程，都需要从用户态转换为核心态，状态转换需要耗费很多CPU时间，所以Synchronized是java中的重量级操作，在有必要时才使用。JVM会优化，比如加入 自旋锁

> ReentrantLock, 与Synchronized同，lock & unlock，并加入了一些高级功能

- 等待可中断 --- lock.lockinteruptlly()
- 可实现公平锁 -- Constructor里边， fair = true.
- 锁可以绑定多个条件， newCondition

2. 非阻塞同步 (乐观并发策略)

> 需要硬件指令来完成，具有原子性----比较并交换(Compare and Swap,  CAS)

> CAS需要3个操作数：内存地址V, 旧的预期A, 新值B

> 当且仅当V符合旧预期值A时， CPU才用新值B更新V的值

> sun.misc.Unsafe提供了compareAndSwapInt()等方法，通过JIT出来结果就是一条平台相关的CPU CAS指令， 而在 java.utils.concurrent包里提供了 compareAndSet(), incrementAndGet()方法。

> "ABA"问题: V符合旧预期的A时， 中间时间线程修改为B,然后又修改为A, 这样CAS仍会发生

3. 无同步方案:

> 一个方法本来就不涉及共享数据，无须同步措施保证它的正确性

> 可重入代码

> 线程本地存储(Thread local Storage): Java  ThreadLocal，变量被某个线程独享


### 锁优化
1. 自旋锁与自适应自旋(Adaptive Spinning)

> 让后面请求锁的那个线程“稍等一下”,但不放弃CPU的执行时间，看看持有锁的线程是否很快会释放锁，只需要让线程执行一个忙循环(自旋)

> 可以减少线程切换的开销，挂起和恢复线程都要转入内核态中完成

> -XX: +UseSpinning, -XX: PreBlockSpin 默认自旋10次

> 自适应自旋锁: 自旋时间不固定，上次成功则多等待一下，上次不成功则不进行自旋等待了

2. 锁消除(Lock Elimination)

> JIT运行时，对一些代码上要求同步，但被检测到不可能存在共享数据竞争的锁进行消除

> 锁消除的主要依据是  逃逸分析 。 如果判断在一段代码中，堆上的数据都不会逃逸出去从而被其他线程访问到，那就把它们当做栈上数据对待，认为它们是线程私有的。

```
 public String concatString(String s1, String s2, String s3) {
     return s1 + s2 + s3;
 }
 JIT 后 ==> 
 public String concatString(String s1, String s2, String s3) {
    StringBuffer sb=new StringBuffer();
    sb.append(s1);
    sb.append(s2);
    sb.append(s3);
    return sb.toString();
 }
```

> 由于append()方法有同步块，但锁sb对象局部变量，不会有方法逃逸，虽然append()有锁,但也会被JIt进行锁消除。

3. 锁粗化(Lock Coarsening)

> 对同一对象重复加锁，影响性能和开销，如上，反复对sb加锁，此时只要将锁 第一个append 和 最后一个append后就可以， 属于JVM 锁优化

4. 轻量级锁(Lightweight Locking)

> 不是用来替代重量级锁的，而是在没有多纯种竞争下，减少传统的重量级锁的互拆带来的性能消耗。

> 对象头 Mark Word <---> Lock Record 交换

> 如果有两条以睥线程争同一个锁，那轻量级锁就膨胀为重量级锁。

> 轻量级锁能提升性能同步性能的依据是"对于绝大部分的锁，在整个同步周期内都是不存在竞争的"，这是 一个经验数据。如果没有竞争，轻量级锁使用CAS操作避免了使用互斥量的开锁，但如果存在锁竞争，除了互斥量的开锁，还额外发生了CAS操作，比单独使用重量级锁会更慢。

5. 偏向锁(Biased Locking)

> 如果说轻量级锁是在无竞争的情况下使用CAS操作去消除同步使用的互斥量，那偏向锁就是在无竞争的情况下把整个同步都消除掉。

> 当锁对象第一次被线程获取时，虚拟机会把对象头中的标志位为01，同时使用CAS将线程ID写入Mark Word.如果成功，持有偏向锁的线程以后每次进入这个锁的同步块时，都可以不再进行同步操作。

> 当有另外一个线程去尝试获取这个锁时，偏向模式退出，切换为 轻量级锁模式。









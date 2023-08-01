模板模式，全称是模板方法设计模式，英文是 Template Method Design Pattern。

在 GoF 的《设计模式》一书中，它是这么定义的：

Define the skeleton of an algorithm in an operation, deferring some steps to subclasses. Template Method lets subclasses redefine certain steps of an algorithm without changing the algorithm’s structure.

翻译成中文就是：模板方法模式在一个方法中定义一个算法骨架，并将某些步骤推迟到子类中实现。模板方法模式可以让子类在不改变算法整体结构的情况下，重新定义算法中的某些步骤。

这里的“算法”，我们可以理解为广义上的“业务逻辑”，并不特指数据结构和算法中的“算法”。

这里的算法骨架就是“模板”，包含算法骨架的方法就是“模板方法”，这也是模板方法模式名字的由来。

## 作用
1. 复用
2. 扩展

在模板模式经典的实现中，模板方法定义为 final，可以避免被子类重写。需要子类重写的方法定义为 abstract，可以强迫子类去实现。

比如AbstractList.addAll

```java

public boolean addAll(int index, Collection<? extends E> c) {
    rangeCheckForAdd(index);
    boolean modified = false;
    for (E e : c) {
        add(index++, e);
        modified = true;
    }
    return modified;
}

public void add(int index, E element) {
    throw new UnsupportedOperationException();
}
```

## 回调

A 调用 B，B 反过来又调用 A，这种调用机制就叫作“回调”

从应用场景上来看，同步回调看起来更像模板模式，异步回调看起来更像观察者模式。

JdbcTemplate 通过回调的机制，将不变的执行流程抽离出来，放到模板方法 execute() 中，将可变的部分设计成回调 StatementCallback，由用户来定制。query() 函数是对 execute() 函数的二次封装，让接口用起来更加方便

## 比较

1. 从应用场景上来，同步回调跟模板模式几乎一致。它们都是在一个大的算法骨架中，自由替换其中的某个步骤，起到代码复用和扩展的目的。而异步回调跟模板模式有较大差别，更像是观察者模式。
2. 从代码实现上来看，回调和模板模式完全不同。回调基于组合关系来实现，把一个对象传递给另一个对象，是一种对象之间的关系；模板模式基于继承关系来实现，子类重写父类的抽象方法，是一种类之间的关系。
3. 在代码实现上，回调相对于模板模式会更加灵活，主要体现在下面几点。像 Java 这种只支持单继承的语言，基于模板模式编写的子类，已经继承了一个父类，不再具有继承的能力。回调可以使用匿名类来创建回调对象，可以不用事先定义类；而模板模式针对不同的实现都要定义不同的子类。如果某个类中定义了多个模板方法，每个方法都有对应的抽象方法，那即便我们只用到其中的一个模板方法，子类也必须实现所有的抽象方法。而回调就更加灵活，我们只需要往用到的模板方法中注入回调对象即可。
4. 有个关键的点确实是一个本质的区别：模板基于继承实现子类可以复用父类的方法；而callback基于类在组合关系实现，callback类无法获取到模板类中定义的方法；
5. 模板方法就是定义一个流程，每个流程结点可变的就是一个抽象spi，由不同实现去现。
   解决的是一个复用与扩展问题。复用的是这个流程本身以及某些结点可以是默认实现。扩展的是有些结点是可以有不同实现的场景。
   回调是一种交互方式，由调用者告诉被调用者：你做完后还要做一个事情，这个事情是什么。然后被调用者做完后就可以做这个指定的事情。回调倒不用强制和模板方法概念合在一起。


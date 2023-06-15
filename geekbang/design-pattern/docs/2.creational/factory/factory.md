现在，我们上升一个思维层面来看工厂模式，它的作用无外乎下面这四个。这也是判断要不要使用工厂模式的最本质的参考标准。
1. 封装变化：创建逻辑有可能变化，封装成工厂类之后，创建逻辑的变更对调用者透明。
2. 代码复用：创建代码抽离到独立的工厂类之后可以复用。
3. 隔离复杂性：封装复杂的创建逻辑，调用者无需了解如何创建对象。
4. 控制复杂度：将创建代码抽离出来，让原本的函数或类职责更单一，代码更简洁。

## DI设计
一个简单的 DI 容器的核心功能一般有三个：配置解析、对象创建和对象生命周期管理，我们可以先参考spring的设计，
1. 配置解析
2. 对象创建，一般采用反射
3. 生命周期，在 Spring 框架中，我们可以通过配置 scope 属性，来区分这两种不同类型的对象。scope=prototype 表示返回新创建的对象，scope=singleton 表示返回单例对象。 除此之外，我们还可以配置对象是否支持懒加载。如果 lazy-init=true，对象在真正被使用到的时候（比如：BeansFactory.getBean(“userService”)）才被被创建；如果 lazy-init=false，对象在应用启动的时候就事先创建好。不仅如此，我们还可以配置对象的 init-method 和 destroy-method 方法，比如 init-method=loadProperties()，destroy-method=updateConfigFile()。DI 容器在创建好对象之后，会主动调用 init-method 属性指定的方法来初始化对象。在对象被最终销毁之前，DI 容器会主动调用 destroy-method 属性指定的方法来做一些清理工作，比如释放数据库连接池、关闭文件。

## 问题
**如何解决循环依赖**

1. 构造器循环依赖
构造器注入的循环依赖是无法解决的，只能抛出bean创建异常使容器无法启动
如何判断是循环依赖？
把正在创建的bean放入到一个(正在创建的map)中，如果依赖创建bean在此map中存在，则抛出异常。
2. setter方法循环依赖
A. 单例情况可以解决循环依赖，方法是提前暴露一个返回该单例的工厂方法，让依赖对象可以引用到
B. 多例不能解决循环依赖，因为多例不需要缓存

三级缓存源码对应
org.springframework.beans.factory.support.DefaultSingletonBeanRegistry#getSingleton
```java
/**
	 * Return the (raw) singleton object registered under the given name.
	 * <p>Checks already instantiated singletons and also allows for an early
	 * reference to a currently created singleton (resolving a circular reference).
	 * @param beanName the name of the bean to look for
	 * @param allowEarlyReference whether early references should be created or not
	 * @return the registered singleton object, or {@code null} if none found
	 */
	@Nullable
	protected Object getSingleton(String beanName, boolean allowEarlyReference) {
		Object singletonObject = this.singletonObjects.get(beanName);
		if (singletonObject == null && isSingletonCurrentlyInCreation(beanName)) {
			synchronized (this.singletonObjects) {
				singletonObject = this.earlySingletonObjects.get(beanName);
				if (singletonObject == null && allowEarlyReference) {
					ObjectFactory<?> singletonFactory = this.singletonFactories.get(beanName);
					if (singletonFactory != null) {
						singletonObject = singletonFactory.getObject();
						this.earlySingletonObjects.put(beanName, singletonObject);
						this.singletonFactories.remove(beanName);
					}
				}
			}
		}
		return singletonObject;
	}


	/** Cache of singleton objects: bean name to bean instance. */
	private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>(256);

	/** Cache of singleton factories: bean name to ObjectFactory. */
	private final Map<String, ObjectFactory<?>> singletonFactories = new HashMap<>(16);

	/** Cache of early singleton objects: bean name to bean instance. */
	private final Map<String, Object> earlySingletonObjects = new HashMap<>(16);
```
## 参考连接
https://www.cnblogs.com/frank223/p/11832079.html

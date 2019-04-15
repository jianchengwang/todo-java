---
title: Java IO
categories: 
- Java
  tags: 
- io
- jenkov

---

## 前言

最近把 javase  过一遍，感觉 [jenkov](http://tutorials.jenkov.com) 大佬的入门教程还不错，所以这里就翻译过来，加一些自己的看法，作为记录。

本文主要记录 io 相关，io 一般都是输入输出，读跟写相对的，所以基本写法差不多，然后不同流处理数据类型不一样，通过包装流转换成其他类型的流即可。

[原文地址](http://tutorials.jenkov.com/java-io)

## pipes

java io 中的 **Pipes** 提供在同一个 **JVM** 中两个 **thread** 通信的能力，同样可以作为源数据流或者目标数据流。你不能使用 Pipes 对不同 JVM(不同的Process) 的 thread 进行通信。Java 所支持的Pipes跟Unix/Linux提供的不大一样，Unix/Linux可以通过Pipes管道流连接不同寻址地址的Process，而Java提供的管道流必须运行在同一Process中，但是可以在不同Thread中。

java 创建 pipes 使用 **PipedOutputStream** 跟 **PipedInputStream** 类。PipedOutputStream应该跟 PipedInputStream连接起来，相辅相成。一个线程写入数据到 PipedInputStream ， 另一个线程可以从PipedOutputStream 读取与之相连的PipedInputStream 写入的数据。

下面有一个管道流的简单例子：

```java
 public static void main(String[] args) throws Exception {

        final PipedOutputStream output = new PipedOutputStream();
        final PipedInputStream input  = new PipedInputStream(output);


        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    output.write("Hello world, pipe!".getBytes());
                } catch (IOException e) {
                }
            }
        });


        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int data = input.read();
                    while(data != -1){
                        System.out.print((char) data);
                        data = input.read();
                    }
                } catch (IOException e) {
                }
            }
        });

        thread1.start();
        thread2.start();

    }

```

需要注意，当使用两个连接的管道流时，将一个流传递给一个线程，另一个流传递给另一个线程。流上的read()和write()调用正在阻塞，这意味着如果您试图同时使用同一个线程进行读和写，这可能会导致线程本身死锁。

## Byte & Char Array

字节数组和字符数组通常在Java中用于在应用程序内部临时存储数据。因为这样的数组也是数据的公共来源或目的地。

### Reading Arrays via InputStream or Reader

从数组中读取数据，您必须将byte或char数组包装在**ByteArrayInputStream**或**CharArrayReader**中。通过这种方式，可以通过包装流或读取器读取数组中可用的字节或字符。

下面是一个简单的例子

```java
byte[] bytes = new byte[255];

//write data into byte array...
for(Integer i=0; i<255; i++) {
    bytes[i] = i.byteValue();
}

InputStream input = new ByteArrayInputStream(bytes);

//read first byte
int data = input.read();
while(data != -1) {
    //do something with data

    //read next byte
    data = input.read();

    System.out.println(data);
}
```

字符数组类似，使用 **CharArrayReader**  替换即可。

### Writing to Arrays via OutputStream or Writer

也可以将数据写入**ByteArrayOutputStream**或**CharArrayWriter**。您所要做的就是创建ByteArrayOutputStream或CharArrayWriter，并将数据写入其中，就像写入任何其他流或writer一样。一旦将所有数据写入其中，只需调用toByteArray()或toCharArray方法，所有写入的数据都以数组形式返回。

```java
CharArrayWriter writer = new CharArrayWriter();

writer.write("This text is converted to bytes".toCharArray());

char[] chars = writer.toCharArray();
```

## System.in & System.out & System.err

这三个流在JVM启动时由Java运行时initialized，所以您不必自己实例化任何流(尽管您可以在运行时交换它们)。

**System.in** 是一个InputStream，它通常连接到控制台程序的键盘输入。系统。由于数据通常通过命令行参数或配置文件传递给命令行Java应用程序，所以in不太常用。在带有GUI的应用程序中，应用程序的输入是通过GUI提供的。这是一个独立于Java IO的输入机制。

**System.out** 是一个OutputStream。System.out通常会将您写入的数据输出到控制台。这通常从命令行工具等仅控制台程序中使用。这也经常用于打印程序的调试语句(尽管这可能不是从程序中获取调试信息的最佳方式)。

**System.err** 是一个PrintStream。System.err就像一个System.out，但通常只用于输出错误文本。一些程序(如Eclipse)将向系统显示输出。红色文本中的err，以便更明显地显示它是错误文本。

尽管这三个系统流是java.lang.System类的静态成员，并且在JVM启动时被预先实例化，您也可以更改每个流要使用的流。只需为系统输入设置一个新的输入流或为系统输出或系统输出设置一个新的输出流，所有进一步的数据都将被读取/写入新的流。

要设置新的系统流，请使用以下方法之一:System.setIn()、System.setOut()或System.setErr()。

下面是一个简单的例子

```java
OutputStream output = new FileOutputStream("c:\\data\\system.out.txt");
PrintStream printOut = new PrintStream(output);

System.setOut(printOut);
```

## Stream

主要根据传输数据是字节类型还是字符类型，分为字节流，例如InputStream，OutputStream，包装一下变为DataInputStream
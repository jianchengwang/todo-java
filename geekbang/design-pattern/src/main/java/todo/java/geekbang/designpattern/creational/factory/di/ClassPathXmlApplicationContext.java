package todo.java.geekbang.designpattern.creational.factory.di;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author jianchengwang
 * @date 2023/6/14
 */
public class ClassPathXmlApplicationContext implements ApplicationContext {

    private BeansFactory beansFactory;
    private BeanConfigParser beanConfigParser;

    public ClassPathXmlApplicationContext(String configLocation) {
        // 1. 读取配置文件
        // 2. 解析配置文件
        // 3. 根据配置信息，生成类定义
        // 4. 把类定义放到 Bean 容器中
        this.beansFactory = new BeansFactory();
        this.beanConfigParser = new XmlBeanConfigParser();
        loadBeanDefinitions(configLocation);
    }

    private void loadBeanDefinitions(String configLocation) {
        InputStream in = null;
        try {
            in = this.getClass().getResourceAsStream("/" + configLocation);
            if (in == null) {
                throw new RuntimeException("Can not find config file: " + configLocation);
            }
            List beanDefinitions = beanConfigParser.parse(in);
            beansFactory.addBeanDefinitions(beanDefinitions);
        }
        finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) { // TODO: log error
                }
            }
        }
    }

    @Override
    public Object getBean(String beanId) {
        return beansFactory.getBean(beanId);
    }
}

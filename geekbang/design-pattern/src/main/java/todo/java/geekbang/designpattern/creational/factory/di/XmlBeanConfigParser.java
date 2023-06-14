package todo.java.geekbang.designpattern.creational.factory.di;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jianchengwang
 * @date 2023/6/14
 */
public class XmlBeanConfigParser implements BeanConfigParser {

    @Override
    public List<BeanDefinition> parse(InputStream inputStream) {
        String content = null;
        // TODO:...
        return parse(content);
    }

    @Override
    public List<BeanDefinition> parse(String configContent) {
        List<BeanDefinition> beanDefinitions = new ArrayList<>();
        // TODO:...
        return beanDefinitions;
    }

}

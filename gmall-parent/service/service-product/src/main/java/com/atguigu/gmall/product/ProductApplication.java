package com.atguigu.gmall.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

/**
 * 商品管理微服务的启动类
 */
@SpringBootApplication//标识位一个boot的项目,4.做了什么事情?
@EnableDiscoveryClient//注册到注册中心
@ComponentScan("com.atguigu.gmall")//包扫描
public class ProductApplication {

    /**
     * 1.什么是springboot?
     *      不是什么新的框架或新的高级框架
     *          --->对以前的ssm类型的项目中的问题进行优化后的一套简化的工具
     *      SSM框架---> Spring+ SpringMVC + Mybatis
     *          a.spring.xml(bean 包扫描)
     *          b.springmvc.xml(bean 拦截器)
     *          c.spring-mybatis.xml(bean 数据源)
     *          d.web.xml(启动配置 过滤器)
     *     问题:
     *       1.配置文件太多了,太复杂了
     *       2.jar包太多了,版本乱七八糟
     *      解决以上两个问题(原理):
     *      1.起步依赖--->jar包太多了,版本乱七八糟
     *          a.maven的依赖具有传递性
     *          b.通过spring官方给你统计好的所有项目可能使用的版本号
     *          c.所有的项目不需要去操心版本的问题
     *          d.利用官方统计好的整合号的坐标,减少pom中依赖的数量
     *      2.自动配置(装配)--->配置文件太多了,太复杂了
     *
     * 2.为什么一个mian中SpringApplication.run就能运行起来一个tomcat?
     * @param args: JVM参数 -Xxm 512m -Xxs 512m
     */
    public static void main(String[] args) {
        //3.代码做了什么事情?
        /**
         * 1.静态方法,创建了一个ApplicationContext是BeanFactory的一个子类
         *      ApplicationContext--->IOC容器(管理 存储 bean)
         *          鱼塘(IOC)--->鱼(bean)?
         * 2.加载启动类的字节码
         * 3.识别到SpringBootApplication这个注解
         *      @SpringBootConfiguration: 标识启动类为一个配置类
         *      @ComponentScan: 包扫描-->
         *          a.启动类所在的包下的全部的类的注解
         *          b.启动类所在的包下的子包中所有类的注解: 自定义的bean全部注入到容器中去
         *      @EnableAutoConfiguration: 自动配置/装配(将可能使用到的所有bean完成初始化)
         *          例如:resdisTemplete RabbitTemplate datasouce
         *          a.依据与pom文件和官方统计的文件中所有的bean,反射实例化成功的注入IOC容器
         *          b.项目环境相关的初始配置也完成了定义,若用户自定义了则用用户的,否则用默认的
         */
        ApplicationContext applicationContext =
                SpringApplication.run(ProductApplication.class, args);
    }

    /**
     * 初始化RestTemplate,将这个对象在启动类加载完成以后,注入到IOC容器中去bean=restTemplate
     *
     * @return
     */
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}

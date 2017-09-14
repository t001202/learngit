package com.example.commandlinerunner;
/**
 * 通过CommandLineRunner，可在所有Spring Bean和 ApplicationContext被创建后执行一些可以访问命令行参数的任务。
 * 如想指定多个CommandLineRunnerBean的执行顺序，
 * 可以实现org.springframework.core.Ordered接口
 * 或添加org.springframework.core.annotation.Order注解
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CommandlinerunnerApplication {
	public static void main(String[] args) {
		SpringApplication.run(CommandlinerunnerApplication.class, args);
	}
}

/**
 * org.springframework.core.annotation.AnnotationAwareOrderComparator负责对CommandLineRunnerBean进行排序。排序规则为：
 如果有一方是org.springframework.core.PriorityOrdered接口实现，而另一方不是，则PriorityOrdered接口实现一方获胜；
 检查org.springframework.core.Ordered接口或 org.springframework.core.annotation.Order 注解获得order，值小者胜；
 其他没有order的则置为Ordered.LOWEST_PRECEDENCE，顺序不定。
 在上述测试中，MyCmdLineRunner2的order为5，MyCmdLineRunner1的order为6，因此MyCmdLineRunner2在MyCmdLineRunner1之前执行。
 Application的demo1和demo2方法设置了@order注解，但是调试可知lamda表达式生成类并没有@order注解信息，因此执行顺序排在后面。
 这是需要注意的地方。此外，Bean初始化顺序跟CommandLineRunner执行顺序也没有关系。
 */

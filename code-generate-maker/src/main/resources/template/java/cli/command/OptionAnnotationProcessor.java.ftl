package ${basePackage}.cli.command;

import picocli.CommandLine.Option;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 获取命令行中所有选项，用于拼接到用户输入的命令后面，从而实现逐步引导用户交互输入
 *
 * @author ${author}
 */
public class OptionAnnotationProcessor {
    public static List<String> processFields(Class<?> clazz) {
        List<String> attribute = new ArrayList<>();
        // 获取所有成员变量
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            // 找到带有@Option注解的成员变量
            if (field.isAnnotationPresent(Option.class)) {
                // 获取@Option注解信息
                Option optionAnnotation = field.getAnnotation(Option.class);
                // 获取@Option注解中的names选项的值
                String[] names = optionAnnotation.names();
                if (names.length > 0 && optionAnnotation.required()) {
                    // 当names属性存在多个值时取其中一个即可
                    attribute.add(names[0]);
                }
            }
        }
        return attribute;
    }
}
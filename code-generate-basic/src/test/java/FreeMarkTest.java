import cn.hutool.core.date.DateUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FreeMark测试类
 */
public class FreeMarkTest {
    @Test
    public void test() throws IOException, TemplateException {
        // 创建Configuration对象，参数版本号与pom文件中保持一致
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);
        // 指定模板文件存放路径
        configuration.setDirectoryForTemplateLoading(new File("src\\main\\resources\\templates"));
        // 指定模板文件字符集
        configuration.setDefaultEncoding("utf-8");
        // 创建模板对象，加载指定模板
        Template template = configuration.getTemplate("testFreeMark.html.ftl");
        // 创建数据模型
        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("currentTime", DateUtil.date().toDateStr());
        List<Map<String, Object>> cityList = new ArrayList<>();
        Map<String, Object> city1 = new HashMap<>();
        city1.put("name", "北京");
        city1.put("remark", "首都、政治中心、文化中心");
        Map<String, Object> city2 = new HashMap<>();
        city2.put("name", "上海");
        city2.put("remark", "魔都、金融中心");
        Map<String, Object> city3 = new HashMap<>();
        city3.put("name", "广州");
        city3.put("remark", "羊城、商贸中心");
        cityList.add(city1);
        cityList.add(city2);
        cityList.add(city3);
        dataModel.put("cityList", cityList);
        // 生成数据渲染后的文件
        Writer out = new FileWriter("testFreeMark.html");
        template.process(dataModel, out);
        // 关闭流
        out.close();
    }
}

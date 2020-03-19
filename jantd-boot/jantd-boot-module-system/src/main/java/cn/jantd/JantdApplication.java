package cn.jantd;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Author xiagf
 * @Date: 2019/07/10
 * @description: Jantd 启动类
 */
@Slf4j
@EnableSwagger2
@SpringBootApplication
public class JantdApplication {

    /**
     * title : 姓名
     * dataIndex : name
     * scopedSlots : {"customRender":"name"}
     * align : center
     */

    private String title;
    private String dataIndex;
    private ScopedSlotsBean scopedSlots;
    private String align;

    public static void main(String[] args) throws UnknownHostException {

        ConfigurableApplicationContext application = SpringApplication.run(JantdApplication.class, args);
        Environment env = application.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        String path = env.getProperty("server.servlet.context-path");

        log.info("\n----------------------------------------------------------\n\t" +
                "Application Jantd-Boot is running! Access URLs:\n\t" +
                "Local: \t\thttp://localhost:" + port + path + "/\n\t" +
                "External: \thttp://" + ip + ":" + port + path + "/\n\t" +
                "swagger-ui: \thttp://" + ip + ":" + port + path + "/swagger-ui.html\n\t" +
                "Doc: \t\thttp://" + ip + ":" + port + path + "/doc.html\n" +
                "----------------------------------------------------------");

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDataIndex() {
        return dataIndex;
    }

    public void setDataIndex(String dataIndex) {
        this.dataIndex = dataIndex;
    }

    public ScopedSlotsBean getScopedSlots() {
        return scopedSlots;
    }

    public void setScopedSlots(ScopedSlotsBean scopedSlots) {
        this.scopedSlots = scopedSlots;
    }

    public String getAlign() {
        return align;
    }

    public void setAlign(String align) {
        this.align = align;
    }

    public static class ScopedSlotsBean {
        /**
         * customRender : name
         */

        private String customRender;

        public String getCustomRender() {
            return customRender;
        }

        public void setCustomRender(String customRender) {
            this.customRender = customRender;
        }
    }
}

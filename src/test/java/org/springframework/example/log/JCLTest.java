package org.springframework.example.log;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

public class JCLTest {

    @Test
    public void testJcl() {
        /*
        JCL 全称为"Jakarta Commons Logging",也可称为"Apache Commons Logging".
        1. 这是JCL的最后发布版本中内置的四种日志技术，硬编码的方式定义个日志实现类数组，优先级也是按数组索引

           private static final String[] classesToDiscover = {
                LOGGING_IMPL_LOG4J_LOGGER(org.apache.commons.logging.impl.Log4JLogger),
                "org.apache.commons.logging.impl.Jdk14Logger",
                "org.apache.commons.logging.impl.Jdk13LumberjackLogger",
                "org.apache.commons.logging.impl.SimpleLog"
        };

         for(int i=0; i<classesToDiscover.length && result == null; ++i) {
            result = createLogFromClass(classesToDiscover[i], logCategory, true);
          }

          JCL 首先会根据内部硬编码定义的数组来适配各个Logger 实现
         */
        Log logger = LogFactory.getLog("");
        printLog(logger, "jcl");

        /*

        java.lang.ClassNotFoundException: org.apache.log4j.Priority
        由于找不大 log4j 的 类，所以直接使用 Jdk14Logger

        logger.getClass() = class org.apache.commons.logging.impl.Jdk14Logger
            十二月 31, 2021 11:40:00 上午  printLog
            信息: info jcl
            十二月 31, 2021 11:40:00 上午  printLog
            警告: warn jcl
            十二月 31, 2021 11:40:00 上午  printLog
            严重: error jcl
            十二月 31, 2021 11:40:00 上午  printLog
            严重: fatal jcl

         */
    }


    @Test
    public void testJcl_log4j() {

        // 1. 导入 log4j:1.2.17 jar，
        // 让 JCL 查找到类Class.forName0() java.lang.ClassNotFoundException: org.apache.log4j.Priorit
        // 2. 配置 log4j.xml 配置
        Log logger = LogFactory.getLog("");
        printLog(logger, "jcl");
        /*
            [log4j] [2021-12-31 15:43:12:509] [INFO ]
            info jcl

            [log4j] [2021-12-31 15:43:12:509] [WARN ]
            warn jcl

            [log4j] [2021-12-31 15:43:12:509] [ERROR]
            error jcl
         */
    }

    @Test
    public void testSpringJCL() {
        /*
              2. Spring-JCL 参考 JCL 做定制化的适配

              org.apache.commons.logging.LogAdapter

                static {
                        if (isPresent(LOG4J_SPI)) {
                            if (isPresent(LOG4J_SLF4J_PROVIDER) && isPresent(SLF4J_SPI)) {
                                // log4j-to-slf4j bridge -> we'll rather go with the SLF4J SPI;
                                // however, we still prefer Log4j over the plain SLF4J API since
                                // the latter does not have location awareness support.
                                logApi = LogApi.SLF4J_LAL;
                            }
                            else {
                                // Use Log4j 2.x directly, including location awareness support
                                logApi = LogApi.LOG4J;
                            }
                        }
                        else if (isPresent(SLF4J_SPI)) {
                            // Full SLF4J SPI including location awareness support
                            logApi = LogApi.SLF4J_LAL;
                        }
                        else if (isPresent(SLF4J_API)) {
                            // Minimal SLF4J API without location awareness support
                            logApi = LogApi.SLF4J;
                        }
                        else {
                            // java.util.logging as default
                            logApi = LogApi.JUL;
                        }
                }

         */
        Log logger = LogFactory.getLog("");
        printLog(logger, "jcl");
    }


    /**
     * <li>trace (the least serious)</li>
     * <li>debug</li>
     * <li>info</li>
     * <li>warn</li>
     * <li>error</li>
     * <li>fatal (the most serious)</li>
     *
     * @param logger
     * @param message
     */
    private void printLog(Log logger, String message) {
        System.out.println("logger.getClass() = " + logger.getClass());
        logger.trace(String.format("trace %s", message));
        logger.debug(String.format("debug %s", message));
        logger.info(String.format("info %s", message));
        logger.warn(String.format("warn %s", message));
        logger.error(String.format("error %s", message));
        logger.fatal(String.format("fatal %s", message));
    }
}

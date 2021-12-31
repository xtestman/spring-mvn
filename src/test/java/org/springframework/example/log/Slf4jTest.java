package org.springframework.example.log;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * https://www.slf4j.org/
 *
 * @author lc
 * @date 2021/12/31
 */
public class Slf4jTest {

    @Test
    public void testSlf4j() {
        /*
            public static ILoggerFactory getILoggerFactory() {
                    if (INITIALIZATION_STATE == 0) {
                        Class var0 = LoggerFactory.class;
                        synchronized(LoggerFactory.class) {
                            if (INITIALIZATION_STATE == 0) {
                                INITIALIZATION_STATE = 1;
                                performInitialization();
                            }
                        }
                    }

                    switch(INITIALIZATION_STATE) {
                    case 1:
                        return SUBST_FACTORY;
                    case 2:
                        throw new IllegalStateException("org.slf4j.LoggerFactory in failed state. Original exception was thrown EARLIER. See also http://www.slf4j.org/codes.html#unsuccessfulInit");
                    case 3:
                        return StaticLoggerBinder.getSingleton().getLoggerFactory();
                    case 4:
                        return NOP_FALLBACK_FACTORY;
                    default:
                        throw new IllegalStateException("Unreachable code");
                    }
                }
         */
        printLog(LoggerFactory.getLogger(this.getClass()));
        /*
        SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
            SLF4J: Defaulting to no-operation (NOP) logger implementation
            SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
            logger.getClass() = class org.slf4j.helpers.NOPLogger
         */
    }

    /**
     * slf4j-log4j12-${latest.stable.version}.jar
     */
    @Test
    public void testSlf4j_log4j() {
        /*
        private static String STATIC_LOGGER_BINDER_PATH = "org/slf4j/impl/StaticLoggerBinder.class";
         */
        printLog(LoggerFactory.getLogger(this.getClass()));
        /*
        logger.getClass() = class org.slf4j.impl.Log4jLoggerAdapter
            [log4j] [2021-12-31 16:53:18:559] [INFO ]
            info Log4jLoggerAdapter

            [log4j] [2021-12-31 16:53:18:560] [WARN ]
            warn Log4jLoggerAdapter

            [log4j] [2021-12-31 16:53:18:560] [ERROR]
            error Log4jLoggerAdapter
         */
    }


    @Test
    public void testSlf4j_multiple_bindings() {
        /*
        SLF4J: Class path contains multiple SLF4J bindings.
        SLF4J: Found binding in [jar:file:/C:/Users/Administrator/.m2/repository/org/slf4j/slf4j-log4j12/1.7.32/slf4j-log4j12-1.7.32.jar!/org/slf4j/impl/StaticLoggerBinder.class]
        SLF4J: Found binding in [jar:file:/C:/Users/Administrator/.m2/repository/org/slf4j/slf4j-jdk14/1.7.32/slf4j-jdk14-1.7.32.jar!/org/slf4j/impl/StaticLoggerBinder.class]
        SLF4J: See http://www.slf4j.org/codes.html#multiple_bindings for an explanation.
        SLF4J: Actual binding is of type [org.slf4j.impl.Log4jLoggerFactory]
         */
        printLog(LoggerFactory.getLogger(this.getClass()));

        /*
        logger.getClass() = class org.slf4j.impl.Log4jLoggerAdapter
            [log4j] [2021-12-31 17:12:12:410] [INFO ]
            info Log4jLoggerAdapter

            [log4j] [2021-12-31 17:12:12:411] [WARN ]
            warn Log4jLoggerAdapter

            [log4j] [2021-12-31 17:12:12:412] [ERROR]
            error Log4jLoggerAdapter
         */
    }


    /**
     * slf4j-jdk14-${latest.stable.version}.jar
     */
    @Test
    public void testSlf4j_jdk14() {
        /*
            slf4j-jdk,slf4j-log4j12 多个绑定器包路径都是相同的，同时都创建了静态的日志绑定器类
             org.slf4j.impl.StaticLoggerBinder
         */
        printLog(LoggerFactory.getLogger(this.getClass()));
        /*
        logger.getClass() = class org.slf4j.impl.JDK14LoggerAdapter
        十二月 31, 2021 5:15:00 下午 org.springframework.example.log.Slf4jTest printLog
        信息: info JDK14LoggerAdapter
        十二月 31, 2021 5:15:00 下午 org.springframework.example.log.Slf4jTest printLog
        警告: warn JDK14LoggerAdapter
        十二月 31, 2021 5:15:00 下午 org.springframework.example.log.Slf4jTest printLog
        严重: error JDK14LoggerAdapter
         */
    }

    /**
     * slf4j-jcl-${latest.stable.version}.jar
     */
    @Test
    public void testSlf4j_jcl() {
        printLog(LoggerFactory.getLogger(this.getClass()));

        /*
        logger.getClass() = class org.slf4j.impl.JCLLoggerAdapter
        十二月 31, 2021 5:21:55 下午 org.slf4j.impl.JCLLoggerAdapter info
        信息: info JCLLoggerAdapter
        十二月 31, 2021 5:21:55 下午 org.slf4j.impl.JCLLoggerAdapter warn
        警告: warn JCLLoggerAdapter
        十二月 31, 2021 5:21:55 下午 org.slf4j.impl.JCLLoggerAdapter error
        严重: error JCLLoggerAdapter
         */
    }

    /**
     * logback-classic-${logback.version}.jar
     */
    @Test
    public void testSlf4j_logback() {
        printLog(LoggerFactory.getLogger(this.getClass()));

        /*
          logger.getClass() = class ch.qos.logback.classic.Logger
            [logback] 行数=169 17:28:55.929 [main] INFO  o.s.example.log.Slf4jTest - info Logger
            [logback] 行数=170 17:28:55.933 [main] WARN  o.s.example.log.Slf4jTest - warn Logger
            [logback] 行数=171 17:28:55.933 [main] ERROR o.s.example.log.Slf4jTest - error Logger
         */
    }

    /**
     * jul-to-slf4j bridge
     */
    @Test
    public void testSlf4j_BridgingLegacyApis() {
        printLog(LoggerFactory.getLogger(this.getClass()));

        /*
        logger.getClass() = class ch.qos.logback.classic.Logger
            [logback] 行数=177 17:42:40.486 [main] INFO  o.s.example.log.Slf4jTest - info Logger
            [logback] 行数=178 17:42:40.490 [main] WARN  o.s.example.log.Slf4jTest - warn Logger
            [logback] 行数=179 17:42:40.490 [main] ERROR o.s.example.log.Slf4jTest - error Logger
            Disconnected from the target VM, address: '127.0.0.1:50351', transport: 'socket'
         */
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
     */
    private void printLog(Logger logger) {
        System.out.println("logger.getClass() = " + logger.getClass());
        String simpleName = logger.getClass().getSimpleName();
        logger.trace(String.format("trace %s", simpleName));
        logger.debug(String.format("debug %s", simpleName));
        logger.info(String.format("info %s", simpleName));
        logger.warn(String.format("warn %s", simpleName));
        logger.error(String.format("error %s", simpleName));
    }
}

package main.jdk8;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Function;

/**
 * Created by liuxj on 2018-03-06.
 */
public class JDK8Feature {

    private static Logger logger = LoggerFactory.getLogger(JDK8Feature.class);

    public static void main(String[] args) {
        Function<String, Integer> string2Integer = (s) -> {s = s + s; return Integer.valueOf(s);};
        Function<Integer, String> integer2String = String::valueOf;
        logger.info(integer2String.apply(string2Integer.apply("890")));
        Function<String, String> back2String = string2Integer.andThen(String::valueOf);
        logger.info(back2String.apply("890"));
        MyFunction<String, Integer, Long> my = FunctionUtil::from;
    }
}

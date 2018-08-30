package main.encoding;

import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;

public class TestStringEncoding {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String str = "A";//[普通字符] 都可识别，unicode需要2字节，其他都只要1字节
//        String str = "ي";//[阿拉伯文] 只有unicode和utf-8可以识别
//        String str = "©";//[不太常用符号] gbk无法识别
//        String str = "♈";//[特需符号] 只有unicode和utf-8可以识别
//        String str = "中";//[中文] ISO无法识别，gbk最短
        System.out.println("default:"+DatatypeConverter.printHexBinary(str.getBytes()));
        System.out.println("Unicode:"+DatatypeConverter.printHexBinary(str.getBytes("Unicode")));
        System.out.println("GBK:"+ DatatypeConverter.printHexBinary(str.getBytes("GBK")));
        System.out.println("utf-8:"+ DatatypeConverter.printHexBinary(str.getBytes("utf-8")));
        System.out.println("ISO-8859-1:"+ DatatypeConverter.printHexBinary(str.getBytes("ISO-8859-1")));
    }
}

package main.db;

public class SC {

    public static void main(String[] args) {
        String a = "gansihao1";
        String key_1 = "930806";
        byte[] bytes_1 = a.getBytes();
        String code_1 = "";
        for (int i = 0; i < bytes_1.length; i++) {
            code_1 += new String(new byte[]{(byte) (bytes_1[i]+Integer.parseInt(key_1.substring(i%key_1.length(),i%key_1.length()+1)))});
        }
        System.out.println(code_1);
//        byte[] bytes_2 = code_1.getBytes();
//        String code_2 = "";
//        for (int i = 0; i < bytes_2.length; i++) {
//            byte b = (byte) (bytes_2[i] + code_1.substring(i % code_1.length(), i % code_1.length() + 1).getBytes()[0] % 10);
//            if (b <97 || b > 122) {
//                b = (byte)(b%10 + 48);
//            }
//            code_2 += new String(new byte[]{b});
//        }
//        System.out.println(code_2);

    }
}

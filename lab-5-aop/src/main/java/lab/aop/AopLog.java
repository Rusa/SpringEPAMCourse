package lab.aop;

public class AopLog {
    private static StringBuffer value = new StringBuffer();

    public static void append(String str){
        value.append(str);
    }
    public static void appendFormat(String str, Object... args){
        value.append(String.format(str, args));
    }

    public static String getStringValue(){
        return value.toString();
    }

    public static void clear(){
        value = new StringBuffer();
    }
}

package TestJavaUtil;

/**
 * @ClassName SingleTon
 * @Description
 * @Author cjx
 * @Date 2022/10/14 18:54
 * @Version 1.0
 */
public class  SingleTon {
    public static void main(String[] args) {
        SingleTon singleTon1=  SingleTon.getInstance();
        SingleTon singleTon2=SingleTon.getInstance();
        System.out.println(singleTon2==singleTon1);
    }
    private static volatile SingleTon singleTon;
    private SingleTon(){

    }
    public static  SingleTon getInstance(){
        if(singleTon==null){
            synchronized (SingleTon.class){
                if(singleTon==null){
                    singleTon=new SingleTon();
                }
            }
        }
        return singleTon;
    }

}

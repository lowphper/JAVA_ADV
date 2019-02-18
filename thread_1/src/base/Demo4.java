package base;

/**
 * lambad表达式写法
 * 1 拷贝小括号，写死右箭头，落地大括号
 * 2 只能用在函数式接口中（就是只有一个接口方法的接口，不包括defaylt默认方法）
 * 3 函数式接口中可以有多个默认方法default
 */
interface  Foo{
    //一个函数式接口中只能有这一个未实现方法，才能使用lambda
    public  int getNum(int a,int b);
    //方法中可以有多个默认方法，不会影响lambda的使用
    public default int getSum(int a,int b){
        return a*b;
    }
    public default int getDev(int a,int b){
        return a/b;
    }
}



public class Demo4 {
    public static void main(String[] args) {
        //传统写法
       Foo f1 =  new Foo(){
            @Override
            public int getNum(int a, int b) {
                return a+b;
            }
        };
        System.out.println( f1.getNum(1,2));
        System.out.println( "===========================美丽得分割线===================");
        //lambda写法
        Foo f2 = (int a,int b)->{
            return a+b;
        };
        System.out.println(f2.getNum(1,2));
        System.out.println(f2.getDev(2,1));
        System.out.println(f2.getSum(1,2));
    }
}

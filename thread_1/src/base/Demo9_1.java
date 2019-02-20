package base;

/**
 * 枚举类示例
 * 使用：声明必须写在第一行
 * 要有构造方法
 * 
 */
public enum Demo9_1 {
    One(1,"齐"),Two(2,"楚"),Three(3,"燕"),Four(4,"赵"),Five(5,"韩"),six(6,"魏");

    private int id;
    private String name;

    public String getName(){
        return name;
    }
    public int getId(){
        return id;
    }

    private Demo9_1(int id,String name){
        this.id = id;
        this.name = name;
    }
    public static Demo9_1 getEnum1(int id){
        Demo9_1[] vals = Demo9_1.values();//获取枚举数组
        for (Demo9_1 d:vals) {
            if(d.id == id){
                return d;
            }
        }
        return null;
    }

}

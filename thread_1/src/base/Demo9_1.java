package base;


import lombok.Getter;

/**
 * 枚举类示例
 */
public enum Demo9_1 {
    @Getter
    private int id;
    private String name;
    private Demo9_1(int id,String name){
        this.id = id;
        this.name = name;
    }
    private Demo9_1(){}
    One(1,"齐"),Two(1,"楚")

}

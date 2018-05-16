package com.desigin_pattern;

/**
 * 模板方法模式
 */
abstract class BaseManger{
    public void action(String name,String method){
        if("admin".equals(name)){
            execute(method);
        }else{
            System.out.println("你不能执行操作，请联系管理员");
        }
    }
    abstract void execute(String method);
}
class UserManger extends BaseManger{
    @Override
    void execute(String method) {
        if("add".equals(method)){
            System.out.println("执行添加操作");
        }else if("delete".equals(method)){
            System.out.println("执行删除操作");
        }else {
            System.out.println("该方法不存在");
        }
    }
}
public class TemplateMethod {
    public static void main(String[] args) {
        BaseManger baseManger = new UserManger();
        baseManger.action("admin","delete");
    }
}

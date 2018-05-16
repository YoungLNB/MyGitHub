package com.desigin_pattern;

/**
 * 策略模式：定义一系列的算法，将每一种算法封装起来并可以相互替换使用，让算法独立于使用它的客户应用
 *     而独立变化；把可变的行为抽象出来，这样做的好处是这些行为可以在真正使用的时候相互替换。
 */
interface SaveThing{
    public void save(String data);
}
class FileSave implements SaveThing{
    @Override
    public void save(String data) {
        System.out.println("将数据保存在文件中");
    }
}
class NetSave implements SaveThing{
    @Override
    public void save(String data) {
        System.out.println("将数据保存在网络中");
    }
}
class SQLSave implements SaveThing{
    @Override
    public void save(String data) {
        System.out.println("将数据保存在数据库中");
    }
}

abstract class BaseService{
    private SaveThing saveThing;

    public void setSaveThing(SaveThing saveThing) {
        this.saveThing = saveThing;
    }
    public void add(String data){
        System.out.println("检查数据的合法性...");
        saveThing.save(data);
        System.out.println("数据保存完毕");
    }
}

class UserService extends BaseService{

}
public class Strategy {
    public static void main(String[] args) {
        BaseService baseService = new UserService();
        baseService.setSaveThing(new FileSave());
        baseService.add("业精于勤荒于嬉，行成于思毁于随");
    }
}

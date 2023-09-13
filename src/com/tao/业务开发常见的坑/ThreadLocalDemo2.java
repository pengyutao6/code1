package com.tao.业务开发常见的坑;

public class ThreadLocalDemo2 {
    public static void main(String[] args) {
        new Service1().process();
    }
}

class Service1{
    public void process(){
        User user = new User("小涛");
        UserContextHolder.threadLocal.set(user);
        new Service2().process();
    }
}

class Service2{
    public void process(){
        System.out.println(UserContextHolder.threadLocal.get().name);
        new Service3().process();
    }
}

class Service3{
    public void process(){
        System.out.println(UserContextHolder.threadLocal.get().name);
    }
}

class UserContextHolder{
    public static ThreadLocal<User> threadLocal = new ThreadLocal<>();
}

class User{
    String name;
    public User(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
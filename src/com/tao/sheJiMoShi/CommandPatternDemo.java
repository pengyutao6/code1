package com.tao.sheJiMoShi;

/**
 * 命令模式
 */
// 命令接口
interface Command {
    void execute();
}

// 具体的命令类
class LightOnCommand implements Command {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn();
    }
}

class LightOffCommand implements Command {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOff();
    }
}

// 接收者类（具体操作）
class Light {
    private boolean isOn = false;

    public void turnOn() {
        isOn = true;
        System.out.println("Light is on");
    }

    public void turnOff() {
        isOn = false;
        System.out.println("Light is off");
    }
}

// 客户端类（发送者）
class RemoteControl {
    private Command command;

    public RemoteControl(Command command) {
        this.command = command;
    }

    public void buttonPress() {
        command.execute();
    }
}

// 测试类
public class CommandPatternDemo {
    public static void main(String[] args) {
        Light light = new Light();
        RemoteControl remoteControl = new RemoteControl(new LightOnCommand(light));
        remoteControl.buttonPress();  // 输出：Light is on
        remoteControl = new RemoteControl(new LightOffCommand(light));
        remoteControl.buttonPress();  // 输出：Light is off
    }
}
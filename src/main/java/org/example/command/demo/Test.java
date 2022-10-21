package org.example.command.demo;

/**
 * @description:
 * @author: YueNandi
 * @time: 2022/2/14 5:40 ����
 */
public class Test {

    public static void main(String[] args) {
        Command command1 = new Command1();
        Command command2 = new Command2();

        new Invoker().execute(command1);
        new Invoker().execute(command2);


        new Invoker().execute(Command.C1);

        new Invoker().execute(Command.C2);
    }
}

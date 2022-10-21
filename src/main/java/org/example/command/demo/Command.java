package org.example.command.demo;

public interface Command {
    public void execute();

    Command C1 = new Command() {
        @Override
        public void execute() {
            System.out.println("执行C1完毕");
        }
    };

    Command C2 = new Command() {
        @Override
        public void execute() {
            System.out.println("执行C2完毕");
        }
    };
}

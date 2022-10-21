package org.example.command.demo;

public interface Command {
    public void execute();

    Command C1 = new Command() {
        @Override
        public void execute() {
            System.out.println("ִ��C1���");
        }
    };

    Command C2 = new Command() {
        @Override
        public void execute() {
            System.out.println("ִ��C2���");
        }
    };
}

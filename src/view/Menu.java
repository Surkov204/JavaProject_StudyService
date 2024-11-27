package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public abstract class Menu {
    private String title;
    private ArrayList<String> options;
    private boolean isStop;

    public Menu() {}

    public Menu(String td, String[] mc) {
        title = td;
        options = new ArrayList<>();
        options.addAll(Arrays.asList(mc));
        this.isStop = false;
    }

    public void display() {
        System.out.println(title);
        System.out.println("--------------------------------");
        for (int i = 0; i < options.size(); i++)
            System.out.println((i + 1) + "." + options.get(i));
        System.out.println("--------------------------------");
    }

    public int getSelected() {
        display();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter selection: ");
        return Integer.parseInt(sc.nextLine());
    }

    public abstract void execute(int choice);

    protected void stop() {
        this.isStop = true;
    }

    public void run() {
        while (!this.isStop) {
            int n = getSelected();
            execute(n);
        }
    }
}
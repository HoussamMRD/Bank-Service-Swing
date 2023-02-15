package metier;

import java.util.Scanner;

public interface interactiveConsole {

    Scanner clavier = new Scanner(System.in);

    default void fermerClavier(){

        clavier.close();

    }
}

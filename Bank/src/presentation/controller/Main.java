package presentation.controller;

import dao.daoFiles.FileBasePaths;
import view.Login;


public class Main {

    public static void main(String[] args) {
        FileBasePaths.createFileBase();
        new Login();

    }
}
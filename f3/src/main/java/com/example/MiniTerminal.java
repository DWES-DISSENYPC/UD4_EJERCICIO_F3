package com.example;

import java.util.Scanner;

public class MiniTerminal {
    public static void main(String[] args) {

        Scanner ent = new Scanner(System.in);
        String comando = "";
        String directorioActual = "G:\\";
        String resultado;

        do {

            comando = terminal(ent, directorioActual);

            if (!comando.equals("exit")) {

                if (comando.equals("pwd")) {

                    resultado = MiniFileManager.pwd(directorioActual);
                    System.err.println(resultado);

                } else if (comando.substring(0, 2).equals("cd")) {

                    resultado = MiniFileManager.cd(directorioActual, comando);

                    if (resultado.equals("La ruta no existe"))
                        System.out.println(resultado);
                    else
                        directorioActual = resultado;

                } else if (comando.equals("ls")) {

                    resultado = MiniFileManager.ls(directorioActual);
                    if (!resultado.equals("correcto"))
                        System.out.println("Algo ha salido mal.");

                } else if ((comando.substring(0, 5)).equals("mkdir")) {

                    resultado = MiniFileManager.mkdir(comando.substring(6), directorioActual);

                } else if (comando.substring(0, 2).equals("rm")) {

                    resultado = MiniFileManager.rm(comando.substring(3), directorioActual);
                    if (!resultado.equals("ok")) System.out.println(resultado);
                }

            }

        } while (!comando.equals("exit"));

    }

    private static String terminal(Scanner ent, String directorioActual) {

        System.out.print(directorioActual + ">  ");

        return ent.nextLine();
    }
}
package com.example;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MiniFileManager {

    public static String pwd(String directorioActual) {

        Path ruta = Paths.get(directorioActual);

        return ruta.toString();

    }

    public static String cd(String directorioActual, String comando) {

        String directorio = comando.substring(3);
        // System.out.println(directorio);

        Path ruta = Paths.get(directorioActual);

        if (directorio.equals("..")) {

            int puntero = directorioActual.lastIndexOf("\\");
            directorioActual = directorioActual.substring(0, puntero);
            return directorioActual;

        } else {

           Path rutaNueva = ruta.resolve(directorio) ;

            if (!Files.isDirectory(rutaNueva)) {

                return "La ruta no existe";
            }
            directorioActual = directorioActual + "\\" + directorio;

        }
        return directorioActual;
    }

    public static String ls(String directorioActual) {

        Path ruta = Paths.get(directorioActual);

        try {

            ArrayList<Path> lista = new ArrayList<>(Files.list(ruta).toList());

            for (Path elemento : lista) {

                System.out.println(elemento.toString());
            }

        } catch (IOException e) {
            return "La operacion ha fallado";

        }

        return "correcto";

    }

    public static String mkdir(String substring, String directorioActual) {

        Path rutaActual = Paths.get(directorioActual);
        Path subDir = Paths.get(substring);
        Path nuevaRuta = rutaActual.resolve(subDir);

        try {

            Files.createDirectory(nuevaRuta);

        } catch (IOException e) {

            return "Algo ha fallado";

        }

        return "correcto";
    }

    public static String rm(String aBorrar, String directorioActual) {

       
        Path ruta = Paths.get(directorioActual);
        Path rutaFinal = ruta.resolve(aBorrar);
        boolean subDir = false;
      
        try {
        
        if (Files.isDirectory(rutaFinal)) {

            

            ArrayList<Path> lista = new ArrayList<>(Files.list(rutaFinal).toList());

            for (Path elemento : lista) {

                if(!Files.isDirectory(elemento)) {

                    Files.delete(elemento);
                    System.out.println("asdfasdfa");

                } else { subDir = true;}
            }

            if (!subDir) {

                 Files.delete(rutaFinal);
                 return "ok";
                 
            }

            return "No se ha podido eliminar Por que tiene subdirectorios";

        

        } else if (!Files.isDirectory(rutaFinal)) {

            Files.delete(rutaFinal);
            return "ok";
        }

        } catch (IOException e) {
            return "La operacion ha fallado";

        }

        return "OK";
    }

}

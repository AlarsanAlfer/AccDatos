package org.example;
import java.io.*;
import java.util.ArrayList;
import static java.lang.String.*;

public class Main {

    public static ArrayList<Pelicula> leer(){
        ArrayList pelis = new ArrayList<Pelicula>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("peliculas.csv"));
            String linea;


            while( (linea = br.readLine()) != null){

                String[] datos = linea.split(",");
                Pelicula pelicula = new Pelicula(Integer.parseInt(datos[0]),datos[1],Integer.parseInt(datos[2]),datos[3],datos[4]);
                pelis.add(pelicula);
            }
            br.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return pelis;
    }


    public static void escribir(ArrayList<Pelicula> pelis) throws IOException {
        StringBuilder plantilla = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader("Plantilla.html"));
        String linea;
        while((linea = br.readLine()) != null){
            plantilla.append(linea).append("\n");
        }

        linea = plantilla.toString();
        for(Pelicula p: pelis){
            String cont = linea
                    .replace("%0%", String.valueOf(p.getId()))
                    .replace("%1%", p.getTitulo())
                    .replace("%2%", String.valueOf(p.getAnho()))
                    .replace("%3%",p.getDirector())
                    .replace("%4%",p.getGenero());

            File carpetaSalida = new File("salida");
            if (!carpetaSalida.exists()) {
                carpetaSalida.mkdir();
            }
            br.close();

            BufferedWriter bw = new BufferedWriter(new FileWriter("salida/"+p.getTitulo()+"-"+p.getId()+".html"));
            bw.write(cont);
            System.out.println("Archivo creado correctamente");
            bw.close();
        }

    }


    public static void main(String[] args) {

        leer();
        try {
            escribir(leer());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
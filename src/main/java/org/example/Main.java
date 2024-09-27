package org.example;
import java.io.*;
import java.util.ArrayList;
import static java.lang.String.*;

public class Main {

    /**
     * Lee la lista de películas desde el archivo CSV
     *
     * Este metodo abre el archivo "peliculas.csv", lee su contenido y crea objetos de la clase pelicula
     * a partir de los datos leidos.
     *
     * Cada película se agrega a una lista que se devuelve al final.
     *
     * @return ArrayList<Pelicula> Una lista de objetos Pelicula que representan las películas leídas del archivo.
     * @throws RuntimeException si el archivo no se encuentra o si ocurre un error de entrada/salida.
     */

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

    /**
     * Escribe información de una lista de películas en archivos HTML.
     *
     * Este metodo lee el documento "plantilla.html", lo copia y almacena para, con
     * cada pelicula, sustituir ciertas claves por los valores que nos interesan de las peliculas y
     * generando un documento html con la información de dicha pelicula.
     * Los archivos html generados se guardan en una carpeta llamada "salida".
     *
     * @param pelis La lista de películas que se usarán para generar los archivos HTML.
     * @throws IOException si ocurre un error durante la lectura o escritura de archivos.
     */

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


    /**
     * El metodo principal que ejecuta la aplicación.
     *
     * Este metodo llama al metodo leer para obtener la lista de películas y luego
     * llama al metodo escribir para generar archivos HTML basados en la lista obtenida.
     */

    public static void main(String[] args) {

        leer();
        try {
            escribir(leer());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
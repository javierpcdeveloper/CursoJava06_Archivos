package datos;

import excepciones.AccesoDatosEx;
import excepciones.EscrituraDatosEx;
import excepciones.LecturaDatosEx;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import modelo.Pelicula;

public class AccesoDatosImpl implements AccesoDatos{
    @Override
    public boolean existe(String nombreRecurso){
        File archivo=new File(nombreRecurso);
        return archivo.exists();
    }
    @Override
    public List<Pelicula> listar(String nombreRecurso) throws LecturaDatosEx {
        List<Pelicula> listado=new ArrayList<>();
        File archivo=new File(nombreRecurso);
        try {
            BufferedReader entrada = new BufferedReader(new FileReader(archivo));
            String linea=entrada.readLine();
            while(linea!=null){
                Pelicula pelicula=new Pelicula(linea);
                listado.add(pelicula);
                linea=entrada.readLine();
            }
            entrada.close();
        } catch (FileNotFoundException ex) {
            //ex.printStackTrace();
            throw new LecturaDatosEx("Excepción al listar peliculas"+ex.getMessage());
        }catch (IOException ex) {
            //ex.printStackTrace();
            throw new LecturaDatosEx("Excepción al listar peliculas"+ex.getMessage());
        }
        return listado;
    }
    @Override
    public void escribir(Pelicula pelicula, String nombreRecurso, boolean anexar) throws EscrituraDatosEx {
        File archivo=new File(nombreRecurso);
        try {
            PrintWriter salida=new PrintWriter(new FileWriter(archivo,anexar));
            salida.println(pelicula.toString());
            salida.close();
            System.out.println("Se ha añadido la película "+pelicula.toString()+" al archivo.");
        } catch (IOException ex) {
            throw new EscrituraDatosEx("Excepcion al añadir película"+ex.getMessage());
        }
    }
    @Override
    public String buscar(String nombreRecurso, String buscar) throws LecturaDatosEx {
        File archivo=new File(nombreRecurso);
        int numLinea=1;
        String resultado="";
        try {
            BufferedReader entrada = new BufferedReader(new FileReader(archivo));
            String linea=entrada.readLine();
            while(linea!=null){
                if(buscar!=null && buscar.equalsIgnoreCase(linea)){
                    resultado="Pelicula "+linea+" encontrada en la línea "+numLinea;
                    break;
                }
                linea=entrada.readLine();
                numLinea++;
            }
            entrada.close();
        } catch (FileNotFoundException ex) {
            //ex.printStackTrace();
            throw new LecturaDatosEx("Excepción al buscar peliculas"+ex.getMessage());
        } catch (IOException ex) {
            throw new LecturaDatosEx("Excepcion al buscar película"+ex.getMessage());
        }
        return resultado;
    }
    @Override
    public void crear(String nombreRecurso) throws AccesoDatosEx {
        File archivo=new File(nombreRecurso);
        try {
            PrintWriter salida=new PrintWriter(new FileWriter(archivo));
            salida.close();
            System.out.println("Se ha creado el archivo");
        } catch (IOException ex) {
            throw new EscrituraDatosEx("Excepcion al crear el archivo"+ex.getMessage());
        }
    }
    @Override
    public void borrar(String nombreRecurso) {
        File archivo=new File(nombreRecurso);
        if(archivo.exists()){
            archivo.delete();
            System.out.println("Se ha borrado el archivo");
        }
    }

    
}

package datos;

import excepciones.*;
import java.util.List;
import modelo.*;

public interface AccesoDatos {
    public boolean existe(String nombreRecurso) throws AccesoDatosEx;
    public List<Pelicula> listar(String nombreRecurso) throws LecturaDatosEx;
    public void escribir(Pelicula pelicula,String nombreRecurso,boolean anexar) throws EscrituraDatosEx;
    public String buscar(String nombreRecurso,String buscar) throws LecturaDatosEx;
    public void crear(String nombreArchivo) throws AccesoDatosEx;
    public void borrar(String nombreArchivo) throws AccesoDatosEx;
}

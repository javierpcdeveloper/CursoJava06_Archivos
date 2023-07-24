package negocio;

import datos.*;
import excepciones.AccesoDatosEx;
import excepciones.LecturaDatosEx;
import java.util.List;
import modelo.*;

public class CatalogoPeliculasImpl implements CatalogoPeliculas{
    private final AccesoDatos accesoDatos;
    
    public CatalogoPeliculasImpl(){
        this.accesoDatos=new AccesoDatosImpl();
    }
    @Override
    public void agregarPelicula(String nombrePelicula) {
        Pelicula pelicula=new Pelicula(nombrePelicula);
        boolean anexar=false;
        try {
            anexar=this.accesoDatos.existe(NOMBRE_RECURSO);
            this.accesoDatos.escribir(pelicula, NOMBRE_RECURSO, anexar);
        } catch (AccesoDatosEx ex) {
            System.out.println("Error de acceso a datos");
        }
    }
    @Override
    public void listarPeliculas() {
        try {
            List<Pelicula> listado=this.accesoDatos.listar(NOMBRE_RECURSO);
            for(Pelicula p:listado){
                System.out.println("Pelicula: "+p);
            }
        } catch (AccesoDatosEx ex) {
            System.out.println("Error de acceso a datos "+ex.getMessage());
        }
    }
    @Override
    public void buscarPelicula(String buscar) {
        String resultado="";
        try {
            resultado=this.accesoDatos.buscar(NOMBRE_RECURSO, buscar);
        } catch (LecturaDatosEx ex) {
            System.out.println("Error al buscar pelicula. "+ex.getMessage());
        }
        System.out.println("El resultado es "+resultado);
    }
    @Override
    public void iniciarCatalogoPeliculas() {
        try {
            if(this.accesoDatos.existe(NOMBRE_RECURSO)){
                this.accesoDatos.borrar(NOMBRE_RECURSO);
                this.accesoDatos.crear(NOMBRE_RECURSO);
            }else{
                this.accesoDatos.crear(NOMBRE_RECURSO);
            }
        } catch (AccesoDatosEx ex) {
            System.out.println("Error al iniciar el catálogo de peliculas. "+ex.getMessage());
        }
    }
}

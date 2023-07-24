package principal;

import negocio.*;
import java.util.Scanner;

public class Principal {
    static Scanner teclado;
    CatalogoPeliculas catalogo;
    public static void main(String[] args) {
        int opcion;
        teclado=new Scanner(System.in);
        CatalogoPeliculas catalogo=new CatalogoPeliculasImpl();
        do{
            opcion=menu();
            switch(opcion){
                case 1:
                    catalogo.iniciarCatalogoPeliculas();
                    break;
                case 2:
                    System.out.print("Nombre de la pelicula a agregar:");
                    catalogo.agregarPelicula(teclado.nextLine());
                    break;
                case 3:
                    catalogo.listarPeliculas();
                    break;
                case 4:
                    System.out.print("Nombre de la pelicula a buscar:");
                    catalogo.buscarPelicula(teclado.nextLine());
                    break;
                case 0:
                    System.out.println("Hasta pronto");
                    break;
                default:
                    System.out.println("Opción incorrecta");
            }
        }while(opcion!=0);
        
    }
    public static int menu(){
        System.out.println("MENU");
        System.out.println("1.Iniciar catálogo");
        System.out.println("2.Agregar película");
        System.out.println("3.Listar películas");
        System.out.println("4.Buscar pelicula");
        System.out.println("0. Salir");
        return Integer.parseInt(teclado.nextLine());
    }
}

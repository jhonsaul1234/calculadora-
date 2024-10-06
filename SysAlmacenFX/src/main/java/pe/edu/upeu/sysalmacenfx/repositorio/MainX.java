package pe.edu.upeu.sysalmacenfx.repositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pe.edu.upeu.sysalmacenfx.modelo.Categoria;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class MainX {
    @Autowired
    CategoriaRepository repository;
    Scanner scanner = new Scanner(System.in);

    public void registro() {
        System.out.println("REGISTRO DE CATEGORIA");
        System.out.print("Ingrese el nombre de la categoría: ");
        String nombre = scanner.nextLine();

        Categoria ca = new Categoria();
        ca.setNombre(nombre);
        Categoria dd = repository.save(ca);
        System.out.println("Categoría registrada:");
        System.out.println(dd.getIdCategoria() + "  " + dd.getNombre());
    }

    public void listar() {
        List<Categoria> datos = repository.findAll();
        System.out.println("ID\tNombre");
        for (Categoria ca : datos) {
            System.out.println(ca.getIdCategoria() + "\t" + ca.getNombre());
        }
    }

    public void eliminar() {
        System.out.print("Ingrese el ID de la categoría a eliminar: ");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Consume newline

        if (repository.existsById(id)) {
            repository.deleteById(id);
            System.out.println("Categoría eliminada con éxito.");
        } else {
            System.out.println("No se encontró la categoría con ID: " + id);
        }
    }

    public void actualizar() {
        System.out.print("Ingrese el ID de la categoría a actualizar: ");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Consume newline

        Optional<Categoria> optionalCategoria = repository.findById(id);
        if (optionalCategoria.isPresent()) {
            Categoria categoria = optionalCategoria.get();
            System.out.print("Ingrese el nuevo nombre de la categoría: ");
            String nuevoNombre = scanner.nextLine();
            categoria.setNombre(nuevoNombre);
            repository.save(categoria);
            System.out.println("Categoría actualizada con éxito.");
        } else {
            System.out.println("No se encontró la categoría con ID: " + id);
        }
    }

    public void menu() {
        int opc = 0;
        String mensaje = "Seleccione una opción: \n1=Crear\n2=Listar\n3=Actualizar\n4=Eliminar\n0=Salir";
        do {
            System.out.println(mensaje);
            opc = Integer.parseInt(scanner.nextLine());
            switch (opc) {
                case 1:
                    registro();
                    break;
                case 2:
                    listar();
                    break;
                case 3:
                    actualizar();
                    break;
                case 4:
                    eliminar();
                    break;
            }
        } while (opc != 0);
        System.out.println("¡jhon saul mamani cruz!");
    }
}
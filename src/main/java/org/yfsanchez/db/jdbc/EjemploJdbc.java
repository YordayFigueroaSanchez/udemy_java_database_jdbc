package org.yfsanchez.db.jdbc;

import org.yfsanchez.db.jdbc.model.Categoria;
import org.yfsanchez.db.jdbc.model.Producto;
import org.yfsanchez.db.jdbc.repository.Repository;
import org.yfsanchez.db.jdbc.repository.RepositoryProductoImpl;
import org.yfsanchez.db.jdbc.utils.ConectionDataBase;

import java.sql.*;
import java.util.Date;

public class EjemploJdbc {
    public static void main(String[] args) {


            Repository<Producto> repositoryProduto = new RepositoryProductoImpl();

            System.out.println(repositoryProduto.porId(1L));

            Producto producto = new Producto();
            producto.setNombre("Test04");
            producto.setPrecio(1234);
            producto.setFecha(new Date());
            Categoria categoria = new Categoria();
            categoria.setId(1L);
            producto.setCategoria(categoria);
            repositoryProduto.guardar(producto);


            repositoryProduto.listar().forEach(p -> System.out.println(p.toString()));

//            Producto producto = new Producto();
//            producto.setNombre("Test03");
//            producto.setPrecio(1222);
//            producto.setFecha(new Date());
//            producto.setId(3L);
//            repositoryProduto.guardar(producto);

    }
}

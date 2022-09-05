package org.yfsanchez.db.jdbc;

import org.yfsanchez.db.jdbc.model.Producto;
import org.yfsanchez.db.jdbc.repository.Repository;
import org.yfsanchez.db.jdbc.repository.RepositoryProductoImpl;
import org.yfsanchez.db.jdbc.utils.ConectionDataBase;

import java.sql.*;
import java.util.Date;

public class EjemploJdbc {
    public static void main(String[] args) {

        try (
                Connection connection = ConectionDataBase.getIntance();
             ) {

            Repository<Producto> repositoryProduto = new RepositoryProductoImpl();
            repositoryProduto.listar().forEach(p -> System.out.println(p.toString()));

            System.out.println(repositoryProduto.porId(1L));

//            Producto producto = new Producto();
//            producto.setNombre("Test03");
//            producto.setPrecio(12);
//            producto.setFecha(new Date());
//            repositoryProduto.guardar(producto);

//            Producto producto = new Producto();
//            producto.setNombre("Test03");
//            producto.setPrecio(1222);
//            producto.setFecha(new Date());
//            producto.setId(3L);
//            repositoryProduto.guardar(producto);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

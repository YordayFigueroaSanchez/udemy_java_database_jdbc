package org.yfsanchez.db.jdbc;

import org.yfsanchez.db.jdbc.model.Producto;
import org.yfsanchez.db.jdbc.repository.Repository;
import org.yfsanchez.db.jdbc.repository.RepositoryProductoImpl;
import org.yfsanchez.db.jdbc.utils.ConectionDataBase;

import java.sql.*;

public class EjemploJdbc {
    public static void main(String[] args) {

        try (
                Connection connection = ConectionDataBase.getIntance();
             ) {

            Repository<Producto> repositoryProduto = new RepositoryProductoImpl();
            repositoryProduto.listar().forEach(p -> System.out.println(p.toString()));

            System.out.println(repositoryProduto.porId(1L));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

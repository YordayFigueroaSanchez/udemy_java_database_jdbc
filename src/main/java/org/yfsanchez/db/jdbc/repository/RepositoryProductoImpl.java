package org.yfsanchez.db.jdbc.repository;

import org.yfsanchez.db.jdbc.model.Categoria;
import org.yfsanchez.db.jdbc.model.Producto;
import org.yfsanchez.db.jdbc.utils.ConectionDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepositoryProductoImpl implements Repository<Producto>{
    private Connection getConnection() throws SQLException {
        return ConectionDataBase.getIntance();
    }

    @Override
    public List<Producto> listar() {
        List<Producto> productos = new ArrayList<>();
        try(
                Statement statement = getConnection().createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT p.*, c.nombre AS categoria " +
                        "FROM productos AS p " +
                        "INNER JOIN categorias AS c ON (p.categoria_id = c.id)");
                ) {
            while (resultSet.next()){
                Producto producto = createProducto(resultSet);
                productos.add(producto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productos;
    }

    private Producto createProducto(ResultSet resultSet) throws SQLException {
        Producto producto = new Producto();
        producto.setId(resultSet.getLong("id"));
        producto.setNombre(resultSet.getString("nombre"));
        producto.setPrecio(resultSet.getInt("precio"));
        producto.setFecha(resultSet.getDate("fecha"));
        Categoria categoria = new Categoria();
        categoria.setId(resultSet.getLong("categoria_id"));
        categoria.setNombre(resultSet.getString("categoria"));
        producto.setCategoria(categoria);
        return producto;
    }

    @Override
    public Producto porId(Long id) {
        Producto producto = null;
        try(PreparedStatement preparedStatement = getConnection().
                prepareStatement("SELECT p.*, c.nombre AS categoria " +
                        "FROM productos AS p " +
                        "INNER JOIN categorias AS c ON (p.categoria_id = c.id) WHERE p.id=?")){
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    producto = createProducto(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return producto;
    }

    @Override
    public void guardar(Producto producto) {
        String sql;
        if (producto.getId() != null && producto.getId() > 0) {
            sql = "UPDATE productos SET nombre=?, precio=?, categoria_id=? WHERE id=?";
        } else {
            sql = "INSERT INTO productos(nombre,precio,categoria_id,fecha) VALUES(?,?,?,?)";
        }
        try(PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1,producto.getNombre());
            preparedStatement.setInt(2,producto.getPrecio());
            preparedStatement.setLong(3,producto.getCategoria().getId());
            if (producto.getId() != null && producto.getId() > 0) {
                preparedStatement.setLong(4, producto.getId());
            } else {
                preparedStatement.setDate(4, new Date(producto.getFecha().getTime()));
            }

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void eliminar(Long id) {
        String sql = "DELETE FROM productos WHERE id=?";
        try(PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

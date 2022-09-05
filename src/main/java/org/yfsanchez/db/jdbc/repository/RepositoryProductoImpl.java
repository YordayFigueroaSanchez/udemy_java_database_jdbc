package org.yfsanchez.db.jdbc.repository;

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
                ResultSet resultSet = statement.executeQuery("SELECT * FROM productos");
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
        return producto;
    }

    @Override
    public Producto porId(Long id) {
        Producto producto = null;
        try(PreparedStatement preparedStatement = getConnection().
                prepareStatement("SELECT * FROM productos WHERE id=?")){
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                producto = createProducto(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return producto;
    }

    @Override
    public void guardar(Producto producto) {

    }

    @Override
    public void eliminar(Long id) {

    }
}

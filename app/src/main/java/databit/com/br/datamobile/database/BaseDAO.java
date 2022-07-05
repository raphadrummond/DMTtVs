package databit.com.br.datamobile.database;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by user on 05/04/2016.
 */
public abstract class BaseDAO<T> {
    protected Context ctx;

    private DatabaseHelper getHelper() {
        return DatabaseManager.getInstance().getHelper();
    }

    public Dao<T, Object> getConnection() {
        return getHelper().getDAO(getEntityClass());
    }

    private Class getEntityClass() {
        ParameterizedType t = (ParameterizedType) getClass().getGenericSuperclass();
        return (Class) t.getActualTypeArguments()[0];
    }

    public List<T> findAll() {
        try {
            return (List<T>) getHelper().getDAO(getEntityClass()).queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.EMPTY_LIST;
        }
    }

    public T findByPK(Object id) {
        try {
            return (T) getHelper().getDAO(getEntityClass()).queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean createOrUpdate(T obj) {
        try {
            return getHelper().getDAO(getEntityClass()).createOrUpdate(obj).getNumLinesChanged() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }

    public boolean delete(T obj) {
        try {
            return getHelper().getDAO(getEntityClass()).delete(obj) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }

    public List<T> findSQL(String sFiltro) {
        try {
            QueryBuilder<T, Object> queryBuilder = getConnection().queryBuilder();
            if (sFiltro != ""){
                Where<T, Object> where = queryBuilder.where();
                where.raw(sFiltro);
            }
            return queryBuilder.query();
        } catch (Throwable t) {
            t.printStackTrace();
            return new ArrayList<>();
        }

    }

    public T findSQLUnique(String sFiltro) {
        try {
            QueryBuilder<T, Object> queryBuilder = getConnection().queryBuilder();
            if (sFiltro != ""){
                Where<T, Object> where = queryBuilder.where();
                where.raw(sFiltro);
            }
            return queryBuilder.queryForFirst();
        } catch (Throwable t) {
            t.printStackTrace();
            return null;
        }

    }

}

package org.izv.dam.roomdatabase.model.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import org.izv.dam.roomdatabase.model.data.Category;

import java.util.List;

@Dao
public interface CategoryDao {

    //CRUD

    @Delete
    int delete(Category category);

    @Update
    int edit(Category category);

    @Query("select * from category where id = :id")
    Category get(long id);

    @Query("select * from category where id = :id")
    LiveData<Category> getLive(long id);

    @Query("select * from category order by category asc")
    List<Category> getCategories();

    @Query("select * from category order by category asc")
    LiveData<List<Category>> getCategoriesLive();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Long insert(Category category);

    //others

    @Query("delete from category")
    int deleteAll();

    @Query("select * from category where category like :category limit 1")
    Category findByCategory(String category);

    @Query("select * from category where category like :category limit 1")
    LiveData<Category> findByCategoryLive(String category);
}
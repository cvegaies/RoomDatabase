package org.izv.dam.roomdatabase.model;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.izv.dam.roomdatabase.model.data.Category;
import org.izv.dam.roomdatabase.model.database.CategoryDao;
import org.izv.dam.roomdatabase.model.database.CategoryDatabase;

import java.util.List;

import static org.izv.dam.roomdatabase.MainActivity.TAG;

public class Repository {

    private CategoryDao categoryDao;
    private LiveData<List<Category>> liveCategoriesList;
    private LiveData<Category> liveGet;
    private MutableLiveData<Integer> liveDelete = new MutableLiveData<>();;
    private MutableLiveData<Long> liveInsert = new MutableLiveData<>();;
    private MutableLiveData<Integer> liveEdit = new MutableLiveData<>();;

    public Repository(Context context) {
        CategoryDatabase categoryDatabase = CategoryDatabase.getDatabase(context);
        categoryDao = categoryDatabase.getCategoryDao();
        liveCategoriesList = categoryDao.getCategoriesLive();
        liveGet = categoryDao.getLive(1);
    }

    public void delete(Category category) {
        CategoryDatabase.databaseWriteExecutor.execute(() -> {
            int r = categoryDao.delete(category);
            Log.d(TAG, r + ": " + category.toString());
        });
    }

    public void deleteLive(Category category) {
        CategoryDatabase.databaseWriteExecutor.execute(() -> {
            liveDelete.postValue(categoryDao.delete(category));
            Log.d(TAG, "live delete");
        });
    }

    public void edit(Category category) {
        CategoryDatabase.databaseWriteExecutor.execute(() -> {
            int r = categoryDao.edit(category);
            Log.d(TAG, r + ": " + category.toString());
        });
    }

    public void editLive(Category category) {
        CategoryDatabase.databaseWriteExecutor.execute(() -> {
            liveEdit.postValue(categoryDao.edit(category));
            Log.d(TAG, "live edit");
        });
    }

    public void get(long id) {
        CategoryDatabase.databaseWriteExecutor.execute(() -> {
            Category category = categoryDao.get(id);
            if(category != null) {
                Log.d(TAG, category.toString());
            } else {
                Log.d(TAG, "null");
            }

        });
    }

    public void getLive(long id) {
        liveGet = categoryDao.getLive(id);
    }

    public void getCategories() {
        CategoryDatabase.databaseWriteExecutor.execute(() -> {
            List<Category> categories = categoryDao.getCategories();
            Log.d(TAG, categories.toString());
        });
    }

    public void getCategoriesLive() {
        liveCategoriesList = categoryDao.getCategoriesLive();
        Log.d(TAG, "get");
    }

    public LiveData<List<Category>> getLiveCategoriesList() {
        return liveCategoriesList;
    }

    public LiveData<Integer> getLiveDelete() {
        return liveDelete;
    }

    public LiveData<Integer> getLiveEdit() {
        return liveEdit;
    }

    public LiveData<Category> getLiveGet() {
        return liveGet;
    }

    public LiveData<Long> getLiveInsert() {
        return liveInsert;
    }

    public void insert(final Category category) {

        //java 8
        CategoryDatabase.databaseWriteExecutor.execute(() -> {
            long id = categoryDao.insert(category);
            Log.d(TAG, "id: " + id);
        });

        //java 7
        /*CategoryDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                long id = categoryDao.insert(category);
                Log.d(TAG, "id: " + id);
            }
        });*/
    }

    public void insertLive(final Category category) {
        CategoryDatabase.databaseWriteExecutor.execute(() -> {
            long r = categoryDao.insert(category);
            liveInsert.postValue(r);
            //liveInsert.setValue(r); # no se puede usar desde una hebra
            Log.d(TAG, "live insert");
        });
    }
}

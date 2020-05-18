package org.izv.dam.roomdatabase.view;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.izv.dam.roomdatabase.model.Repository;
import org.izv.dam.roomdatabase.model.data.Category;

import java.util.List;

public class ViewModel extends AndroidViewModel {

    private Repository repository;

    public ViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository(application);
    }

    public void delete(Category category) {
        repository.delete(category);
    }

    public void deleteLive(Category category) {
        repository.deleteLive(category);
    }

    public void edit(Category category) {
        repository.edit(category);
    }

    public void editLive(Category category) {
        repository.editLive(category);
    }

    public void get(long id) {
        repository.get(id);
    }

    public void getLive(long id) {
        repository.getLive(id);
    }

    public void getCategories() {
        repository.getCategories();
    }

    public void getCategoriesLive() {
        repository.getCategoriesLive();
    }

    public LiveData<List<Category>> getLiveCategoriesList() {
        return repository.getLiveCategoriesList();
    }

    public LiveData<Integer> getLiveDelete() {
        return repository.getLiveDelete();
    }

    public LiveData<Integer> getLiveEdit() {
        return repository.getLiveEdit();
    }

    public LiveData<Category> getLiveGet() {
        return repository.getLiveGet();
    }

    public LiveData<Long> getLiveInsert() {
        return repository.getLiveInsert();
    }

    public void insert(Category category) {
        repository.insert(category);
    }

    public void insertLive(Category category) {
        repository.insertLive(category);
    }
}

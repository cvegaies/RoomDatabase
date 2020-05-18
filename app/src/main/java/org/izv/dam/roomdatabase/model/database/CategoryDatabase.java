package org.izv.dam.roomdatabase.model.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import org.izv.dam.roomdatabase.model.data.Category;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Category.class}, version = 1, exportSchema = false)
public abstract class CategoryDatabase extends RoomDatabase {

    public abstract CategoryDao getCategoryDao();

    private static volatile CategoryDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;

    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static CategoryDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CategoryDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CategoryDatabase.class, "category_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

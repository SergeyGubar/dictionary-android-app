package com.example.Interfaces;

import android.database.Cursor;
import android.widget.ListView;

import java.util.List;

/**
 * Created by Sergey on 8/24/2017.
 */

public interface CategoriesService {
    void addCategory(String categoryName);

    void removeCategory(String categoryName);

    Cursor getAllCategories();

    String getCategoryName(int position, Cursor cursor);

    List<String> getCategoriesNames();
}

package com.example.quanlysv;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ClassManager {
    private String id;
    private  String name;

    public ClassManager(Set<String> set) {
        Iterator<String> iterator = set.iterator();

        if(iterator.hasNext()){
            id = iterator.next();
        }
        if(iterator.hasNext()){
            name = iterator.next();
        }
    }

    public  ClassManager(){}

    public ClassManager(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getId()+" _ "+getName();
    }

    public Set<String> toSet(){
        Set<String> res = new HashSet<>();
        res.add(id);
        res.add(name);
        return res;
    }
}

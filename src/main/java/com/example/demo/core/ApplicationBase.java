package com.example.demo.core;

import java.util.Optional;

import com.example.demo.core.functionalInterfaces.FindById;

public abstract class ApplicationBase<T, ID> {

    private FindById<T, ID> getById;

    protected T findById(ID id){
        Optional<T> t = this.getById.findById(id);
        return t.get();
    }

    protected ApplicationBase(FindById<T, ID> getById){
        this.getById = getById;
    }

    protected String serializeObject(T entity, String messege) {

        return String.format("%s %s succesfully.", entity.toString(), messege);
    }
}

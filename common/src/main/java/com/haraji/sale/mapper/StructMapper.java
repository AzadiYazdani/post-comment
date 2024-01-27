package com.haraji.sale.mapper;

import java.util.List;


public abstract class StructMapper<T, V, R> {


    public abstract T toModel(V entity);

    public abstract List<T> toModelList(List<V> stateEntityList);

    public abstract R toDtoResponse(T entity);

    public abstract List<R> toDtoResponseList(List<T> stateList);

}

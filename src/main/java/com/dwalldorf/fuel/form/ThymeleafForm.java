package com.dwalldorf.fuel.form;

import java.io.Serializable;

public interface ThymeleafForm<T, F> extends Serializable {

    T toModel();

    F fromModel(T model);
}
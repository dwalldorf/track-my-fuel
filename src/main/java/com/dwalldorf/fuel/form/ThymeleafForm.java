package com.dwalldorf.fuel.form;

import java.io.Serializable;

public interface ThymeleafForm<T, F> extends Serializable {

    T toModel(F form);

    F fromModel(T model);
}
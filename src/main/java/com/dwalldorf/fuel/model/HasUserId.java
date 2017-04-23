package com.dwalldorf.fuel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface HasUserId extends HasId {

    Long getUserId();

    @JsonIgnore
    String getObjectType();
}
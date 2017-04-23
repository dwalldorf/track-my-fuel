package com.dwalldorf.fuel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface HasUser extends HasId {

    User getUser();

    @JsonIgnore
    String getObjectType();
}
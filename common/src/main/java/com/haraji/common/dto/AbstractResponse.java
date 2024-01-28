package com.haraji.common.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@Setter
@SuperBuilder
public abstract class AbstractResponse implements Serializable {
    private static final long serialVersionUID = -6332381530908723437L;

    private int status;
    private String message;
}

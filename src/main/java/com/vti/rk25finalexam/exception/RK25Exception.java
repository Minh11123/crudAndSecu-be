package com.vti.rk25finalexam.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)

@NoArgsConstructor
public class RK25Exception
        extends RuntimeException
        implements ICommonException {

    private Rk25Error rk25Error;

    public RK25Exception rk25Error(Rk25Error rk25Error) {
        this.rk25Error = rk25Error;
        return this;
    }

    @Override
    public Rk25Error getRk25Error() {
        return rk25Error;
    }
}

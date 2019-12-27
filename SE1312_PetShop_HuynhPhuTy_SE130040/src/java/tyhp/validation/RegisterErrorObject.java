/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tyhp.validation;

import java.io.Serializable;

/**
 *
 * @author ACER
 */
public class RegisterErrorObject implements Serializable {

    private String errUsername, errConfirm, errDOB;

    public RegisterErrorObject() {
    }

    public String getErrUsername() {
        return errUsername;
    }

    public void setErrUsername(String errUsername) {
        this.errUsername = errUsername;
    }

    public String getErrConfirm() {
        return errConfirm;
    }

    public void setErrConfirm(String errConfirm) {
        this.errConfirm = errConfirm;
    }

    public String getErrDOB() {
        return errDOB;
    }

    public void setErrDOB(String errDOB) {
        this.errDOB = errDOB;
    }

}

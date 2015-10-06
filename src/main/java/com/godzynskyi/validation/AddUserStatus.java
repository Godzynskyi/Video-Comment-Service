package com.godzynskyi.validation;

/**
 * Created by Java Developer on 04.10.2015.
 */
public class AddUserStatus {
    private boolean isAdded=false;
    private boolean isValid=true;

    //Return false if User with this login has already existed
    private boolean isNotUserExist=true;

    //Return false if User confirms password incorrect
    private boolean passwordsEquals=true;

    public boolean isAdded() {
        return isAdded;
    }

    public boolean isValid() {
        return isValid;
    }

    public boolean isNotUserExist() {
        return isNotUserExist;
    }

    public boolean isPasswordsEquals() {
        return passwordsEquals;
    }

    //Able to set FALSE, setting TRUE has no reason
    public AddUserStatus setNotUserExist(boolean isNotUserExist) {
        if(!isNotUserExist) {
            this.isNotUserExist = false;
            isValid=false;
            isAdded=false;
            return this;
        } else {
            this.isNotUserExist = true;
            if(passwordsEquals) isValid = true;
            return this;
        }
    }

    //Able to set FALSE, setting TRUE has no reason
    public AddUserStatus setPasswordsEquals(boolean arePasswordsEquals) {
        if(!arePasswordsEquals) {
            passwordsEquals = false;
            isValid=false;
            isAdded=false;
            return this;
        } else {
            this.passwordsEquals = true;
            if(isNotUserExist) isValid = true;
            return this;
        }
    }

    //Able to set FALSE, setting TRUE has no reason
    public AddUserStatus setAdded(boolean addedToBase) {
        isAdded = addedToBase;
        return this;
    }
}

package ua.com.company.exception;

public class BumperNotFound extends Exception{
    public BumperNotFound(String message) {
        super("Entity with id= "+message+ " in bumpers list not found.");
    }
}

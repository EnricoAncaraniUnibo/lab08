package it.unibo.deathnote.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import it.unibo.deathnote.api.DeathNote;

/**
 * DeathNoteImplementation
 */
public class DeathNoteImplementation implements DeathNote{
    private Map<String,String> mapNameCause;
    private Map<String,String> mapNameDetails;
    
    public Set<String> getDeadPeople() {
        return mapNameCause.keySet();
    }

    public DeathNoteImplementation() {
        mapNameCause = new HashMap<>();
        mapNameDetails = new HashMap<>();
    }

    @Override
    public String getRule(int ruleNumber) {
        throw new IllegalArgumentException();
    }

    @Override
    public void writeName(String name) {
        throw new NullPointerException();
    }

    @Override
    public boolean writeDeathCause(String cause) {
        throw new IllegalStateException();
    }

    @Override
    public boolean writeDetails(String details) {
        throw new IllegalStateException();
    }

    @Override
    public String getDeathCause(String name) {
        throw new IllegalArgumentException();
    }

    @Override
    public String getDeathDetails(String name) {
        throw new IllegalArgumentException();
    }

    @Override
    public boolean isNameWritten(String name) {
        throw new UnsupportedOperationException("Unimplemented method 'isNameWritten'");
    }

    
}
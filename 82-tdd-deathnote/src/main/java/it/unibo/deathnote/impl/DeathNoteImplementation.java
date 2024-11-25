package it.unibo.deathnote.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import it.unibo.deathnote.api.DeathNote;

/**
 * DeathNoteImplementation
 */
public class DeathNoteImplementation implements DeathNote{
    private Map<String,String> mapNameCause;
    private Map<String,String> mapNameDetails;
    private long milliFromLastName;
    private String lastNameWritted;
    
    public Set<String> getDeadPeople() {
        return mapNameCause.keySet();
    }

    public DeathNoteImplementation() {
        mapNameCause = new HashMap<>();
        mapNameDetails = new HashMap<>();
    }

    @Override
    public String getRule(int ruleNumber) {
        if(ruleNumber > 0 && ruleNumber < RULES.size()-1) {
            return RULES.get(ruleNumber);
        }
        throw new IllegalArgumentException("Rule is non existance");
    }

    @Override
    public void writeName(String name) {
        if(Objects.requireNonNull(name) != null) {
            mapNameCause.put(name, "heart attack");
            mapNameDetails.put(name, "");
            milliFromLastName = System.currentTimeMillis();
            lastNameWritted = name;
        }
    }

    @Override
    public boolean writeDeathCause(String cause) {
        if(!mapNameCause.keySet().isEmpty() && cause != null) {
            long m = System.currentTimeMillis();
            if(m - milliFromLastName < 40) {
                mapNameCause.replace(lastNameWritted, cause);
                return true;
            }
            return false;
        }
        throw new IllegalStateException();
    }

    @Override
    public boolean writeDetails(String details) {
        if(!mapNameCause.keySet().isEmpty() && details != null) {
            long m = System.currentTimeMillis();
            if(m - milliFromLastName < 6040) {
                mapNameDetails.replace(lastNameWritted, details);
                return true;
            }
            return false;
        }
        throw new IllegalStateException();
    }

    @Override
    public String getDeathCause(String name) {
        if(mapNameCause.keySet().contains(name)) {
            return mapNameCause.get(name);
        }
        throw new IllegalArgumentException();
    }

    @Override
    public String getDeathDetails(String name) {
        if(mapNameDetails.keySet().contains(name)) {
            return mapNameDetails.get(name);
        }
        throw new IllegalArgumentException();
    }

    @Override
    public boolean isNameWritten(String name) {
        if(mapNameCause.keySet().contains(name)) {
            return true;
        }
        return false;
    }

    
}
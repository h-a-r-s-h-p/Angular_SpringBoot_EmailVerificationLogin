package com.example.Backend.IsDomainExist;

import javax.naming.NamingException;

public interface DomainExistInterface {
    public String getDomain(String emailAddr);
    public int checkIfDomainExist( String hostName ) throws NamingException;
}

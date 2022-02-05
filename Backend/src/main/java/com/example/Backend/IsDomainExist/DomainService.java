package com.example.Backend.IsDomainExist;

import java.util.Hashtable;

import javax.naming.NamingException;
import javax.naming.directory.*;

import org.springframework.stereotype.Service;

@Service
public class DomainService implements DomainExistInterface {

    public String getDomain(String emailAddr){
        String domain="";
        int flag=0;
        for( int i=0;i<emailAddr.length();i++)
        {
            char currChar = emailAddr.charAt(i);
            if(flag==1) domain = domain + currChar;
            if(currChar=='@') flag=1;
        }
        return domain;
    }

    public int checkIfDomainExist( String hostName ) throws NamingException {
        Hashtable env = new Hashtable();
        env.put("java.naming.factory.initial",
                "com.sun.jndi.dns.DnsContextFactory");
        DirContext ictx = new InitialDirContext( env );
        Attributes attrs = ictx.getAttributes( hostName, new String[] { "MX" });
        Attribute attr = attrs.get( "MX" );
        if( attr == null ) return( 0 );
        return( attr.size() );
    }

}

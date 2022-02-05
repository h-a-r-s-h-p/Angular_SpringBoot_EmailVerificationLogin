package com.example.Backend;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("users")
public class Users {
    @Id
    private ObjectId _id;             // This name should be _id only so that mongodb can assign it by itself
    private String email;
    
    public Users(){}                  // This default constructor is compulsory to write

    public Users(ObjectId _id, String email){
        this._id=_id;
        this.email=email;
    }

    // all getters and setters are compulsory to write

    public String getID(){
        return this._id.toHexString();
    }

    public void setID(ObjectId _id){
        this._id=_id;
    }
    public void setEmail(String email){
        this.email=email;
    }

    public String getEmail(){
        return email;
    }

}

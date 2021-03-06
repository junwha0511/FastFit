package com.cse364.domain;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
@Value
@EqualsAndHashCode(of="id")
public class User {
    @Id
    int id;
    @NonNull UserInfo info;

    @PersistenceConstructor
    public User(int id, UserInfo info) {
        this.id = id;
        this.info = info;
    }

    public User(int id, Gender gender, int age, Occupation occupation, String zipCode) {
        this.id = id;
        this.info = new UserInfo(gender, age, occupation, zipCode);
    }

    // Getters
    public Gender getGender() { return info.getGender(); }
    public int getAge() { return info.getAge(); }
    public Occupation getOccupation() { return info.getOccupation(); }
    public String getZipCode() { return info.getZipCode(); }
}

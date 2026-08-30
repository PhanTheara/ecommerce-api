package com.istad.theara.ecommerce_api.features.userProfile;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_profile")
public class UserProfile {
    @Id
    private  String id; //keycloak user id
    @Column(length = 6)
    private  String gender;

    private String biography;
    private String facebookProfile;
    private String telegramProfile;
    private String pictureProfile;

}

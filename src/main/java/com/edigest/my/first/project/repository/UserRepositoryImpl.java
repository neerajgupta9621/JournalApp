package com.edigest.my.first.project.repository;

import com.edigest.my.first.project.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;


import java.util.List;

public class UserRepositoryImpl {

    @Autowired
    private MongoTemplate mongoTemplate;


    public List<User> getUserForSA(){
        Query query = new Query();
        query.addCriteria(Criteria.where("userName").is("satyam"));
//        query.addCriteria(Criteria.where("roles").in("ROLE_USER","ROLE_ADMIN"));
        List<User> users = mongoTemplate.find(query, User.class);
        return users;
    }
}

package com.edigest.my.first.project.repository;

import com.edigest.my.first.project.entity.ConfigJournalAppEntity;
import com.edigest.my.first.project.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigJournalAppRepository extends MongoRepository<ConfigJournalAppEntity, ObjectId> {


}

package com.bobzero.joblisting.repository.imp;

import com.bobzero.joblisting.model.Post;
import com.bobzero.joblisting.repository.SearchRepository;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class SearchRepositoryImpl implements SearchRepository {

    private final MongoClient mongoClient;
    private final MongoConverter mongoConverter;

    public SearchRepositoryImpl(MongoClient mongoClient, MongoConverter mongoConverter) {
        this.mongoClient = mongoClient;
        this.mongoConverter = mongoConverter;
    }

    @Override
    public List<Post> findByText(String text) {
        final List<Post> postList= new ArrayList<>();
        MongoDatabase database = mongoClient.getDatabase("bobzero");
        MongoCollection<Document> collection = database.getCollection("JobPost");
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(
                new Document("$search", new Document("text", new Document("query", text).append("path", "desc"))),
                new Document("$sort", new Document("exp", 1L)),new Document("$limit", 5L)));

        result.forEach(doc -> postList.add(mongoConverter.read(Post.class, doc)));

        return postList;
    }
}

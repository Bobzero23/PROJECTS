package com.bobzero.joblisting.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "JobPost")
@Data
public class Post {
    private String desc;
    private int exp;
    private String profile;
    private String[] techs;
}

package com.thecatapi.model.images;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Image {
    public String id;
    public String url;
}

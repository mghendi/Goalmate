package com.suluhu.goalmate.Explore;

public class ExploreObject {
    private int id;
    private String name, start, link, category, location;

    public ExploreObject(int id, String name, String start, String link, String category, String location) {
        this.id = id;
        this.name = name;
        this.start = start;
        this.link = link;
        this.category = category;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStart() {
        return start;
    }

    public String getLink() {
        return link;
    }

    public String getCategory() {
        return category;
    }

    public String getLocation() {
        return location;
    }
}

package com.bah.cloudengine.domain;

import java.util.ArrayList;

/**
 * Created on:
 * Date: 7/8/14
 * Time: 10:23 PM
 */
public class PostVmTag {
    //    {
//        "action" : "assign",
//            "resources" : [
//        { "category" : "location", "name" : "ny" },
//        { "category" : "department", "name" : "finance" },
//        { "category" : "environment", "name" : "dev" }
//        ]
//    }
    private String action;
    private ArrayList<resource> resources = new ArrayList<resource>();

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ArrayList<resource> getResources() {
        return resources;
    }

    public void setResources(ArrayList<resource> resources) {
        this.resources = resources;
    }

    public static class resource {
        private String category;
        private String name;

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

//    public static void main(String[] args) throws IOException {
//        PostVmTag postVmTag = new PostVmTag();
//        PostVmTag.resource resources = new resource();
//        resources.setCategory("test");
//        resources.setCategory("asdflkj");
//        ArrayList<resource> arrayList = new ArrayList<resource>();
//        arrayList.add(resources);
//        postVmTag.setResources(arrayList);
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.writeValue(System.out,postVmTag);
//    }

}

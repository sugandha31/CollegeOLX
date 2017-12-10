package com.example.sugandhabansal.collegeolx;

/**
 * Created by Sugandha Bansal on 7/30/2017.
 */

public class NewUser {
    String email;
    String name;

    public NewUser(String email,String name){
        this.email = email;
        this.name = name;
    }

    /**
     * Created by Sugandha Bansal on 7/30/2017.
     */

    public static class BuyDto {
        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        private String title;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        private String description;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        private String image;

        public BuyDto(String title,String description,String image){
            this.title = title;
            this.description = description;
            this.image = image;
        }

        public String toString(){
            return "com.example.sugandhabansal.collegeolx.NewUser.BuyDto{" +
                    "title='" + title + '\'' +
                    ", description='" + description + '\'' +
                    ", image='" + image + '\'' +
                    '}';
        }
    }
}

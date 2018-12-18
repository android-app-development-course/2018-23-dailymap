package com.dailymap.model.network;

public class UserInfo {

				/*"user_id":"1",
                        "name":"小白",
                        "password":null,
                        "user_image_path":null,
                        "phone_num":"5354654",
                        "user_intro":null,
                        "user_sex":null,
                        "user_city":null,
                        "user_email":null*/
				private String user_id;
    private String name;
    private String password;
    private String user_image_path;
    private String phone_num;
    private String user_intro;
    private String user_sex;
    private String user_city;
    private String user_email;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_image_path() {
        return user_image_path;
    }

    public void setUser_image_path(String user_image_path) {
        this.user_image_path = user_image_path;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getUser_intro() {
        return user_intro;
    }

    public void setUser_intro(String user_intro) {
        this.user_intro = user_intro;
    }

    public String getUser_sex() {
        return user_sex;
    }

    public void setUser_sex(String user_sex) {
        this.user_sex = user_sex;
    }

    public String getUser_city() {
        return user_city;
    }

    public void setUser_city(String user_city) {
        this.user_city = user_city;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }
}

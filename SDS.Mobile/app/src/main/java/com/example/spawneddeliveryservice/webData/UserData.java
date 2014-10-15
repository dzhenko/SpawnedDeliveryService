package com.example.spawneddeliveryservice.webData;

public class UserData {
    private static String username;
    private static String token;

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        UserData.username = username;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        UserData.token = token;
    }

    public static Boolean isLogged(){
        if(UserData.token!=null){
            return  true;
        }

        return  false;
    }
}

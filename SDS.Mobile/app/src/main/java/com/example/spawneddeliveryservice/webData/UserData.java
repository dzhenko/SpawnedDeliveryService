package com.example.spawneddeliveryservice.webData;

public class UserData {
    private static String mUsername;
    private static String mToken;

    public static String getUsername() {
        return mUsername;
    }

    public static void setUsername(String username) {
        UserData.mUsername = username;
    }

    public static String getToken() {
        return mToken;
    }

    public static void setToken(String token) {
        UserData.mToken = token;
    }

    public static Boolean isLogged(){
        return UserData.mToken!=null;
    }
}

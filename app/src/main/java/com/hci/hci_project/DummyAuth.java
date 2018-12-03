package com.hci.hci_project;

import android.os.AsyncTask;

import java.util.HashMap;

public class DummyAuth {
    private static final HashMap<String, User> USERS = new HashMap<>();
    static{
        USERS.put("test", new User("test", "Luis", "Rivera", "test", false));
        USERS.put("foo@example.com", new User("foo@example.com", "Luis", "Rivera", "hello", false));
        USERS.put("bar@example.com", new User("bar@example.com", "Ernesto", "Sanchez", "world", true));

    }
    protected static User currentUser;

    public static boolean register(String first, String last, String email, String password, boolean isProfessor){
        if(USERS.containsKey(email)) return false;
        USERS.put(email, new User(email, first, last, password, isProfessor));
        return true;
    }

    public static User login(String email, String password){
        if(!USERS.containsKey(email)) return null;
        currentUser = USERS.get(email).getPassword().equals(password)? USERS.get(email) : null;
        return currentUser;
    }

    public static void logout(){
        currentUser = null;
    }

    public static User getCurrentUser(){
        return currentUser;
    }

    public static boolean emailExists(String email){
        return USERS.containsKey(email);
    }


}

class User {
    private String email;
    private String first;
    private String last;
    private String password;
    private boolean isProfessor;

    public User(String email, String first, String last, String password, boolean isProfessor) {
        this.email = email;
        this.first = first;
        this.last = last;
        this.password = password;
        this.isProfessor = isProfessor;
    }

    public String getEmail() {
        return email;
    }

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }

    public String getPassword() {
        return password;
    }

    public boolean isProfessor() {
        return isProfessor;
    }

//    @Override
////    protected Boolean doInBackground(Void... params) {
////        // TODO: attempt authentication against a network service.
////
////        try {
////            // Simulate network access.
////            Thread.sleep(2000);
////        } catch (InterruptedException e) {
////            return false;
////        }
////
////
////
////        if(DUMMY_CREDENTIALS.containsKey(mEmail)){
////            return mPassword.equals(DUMMY_CREDENTIALS.get(mEmail));
////        }
////
////        // TODO: register the new account here.
////        return false;
////    }
////
////    @Override
////    protected void onPostExecute(final Boolean success) {
////        DummyAuth.currentUser = null;
////        showProgress(false);
////
////        if (success) {
////            finish();
////        } else {
////            mPasswordView.setError(getString(R.string.error_incorrect_password));
////            mPasswordView.requestFocus();
////        }
////    }
////
////    @Override
////    protected void onCancelled() {
////        mAuthTask = null;
////        showProgress(false);
////    }

}

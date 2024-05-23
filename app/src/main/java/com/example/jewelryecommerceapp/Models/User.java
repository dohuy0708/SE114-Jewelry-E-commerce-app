package com.example.jewelryecommerceapp.Models;

import android.graphics.Bitmap;

import java.util.Date;

public class User {
    public static final String COLLECTION_NAME = "users";

    private String UID;
    private String NAME;
    private String IMG;
    private String EMAIL;
    private String PHONE;
    private String BIRTHDAY;

    private int GENDER;
    private int TYPE;

    private Bitmap avatar;


    public User()   {

    }

    public  User(String Name, String Phone, String Email, String id)
    {
        this.NAME = Name;
        this.EMAIL = Email;
        this.PHONE = Phone;
        this.UID = id;
    }
    public  User(String Name, String Phone, String Email, String id, String birthday, int gender, Bitmap avatar)
    {

        this.GENDER = gender;
        this.BIRTHDAY = birthday;
        this.NAME = Name;
        this.EMAIL = Email;
        this.PHONE = Phone;
        this.UID = id;
    }
    public User(String NAME, String EMAIL    ) {
        this.NAME = NAME;
        this.EMAIL = EMAIL;
        this.TYPE = 1;
    }



    public User(int TYPE, String NAME, String IMG , String EMAIL, String PHONE) {
        this.NAME = NAME;
        this.IMG = IMG;
        this.EMAIL = EMAIL;

        this.PHONE = PHONE;
        this.TYPE = TYPE;
    }

    public User(String UID, String NAME, String IMG) {
        this.UID = UID;
        this.NAME = NAME ;
        this.IMG = IMG;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public void setIMG(String IMG) {
        this.IMG = IMG;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public void setPHONE(String PHONE) {
        this.PHONE = PHONE;
    }

    public void setBIRTHDAY(String BIRTHDAY) {
        this.BIRTHDAY = BIRTHDAY;
    }

    public void setTYPE(int TYPE) {
        this.TYPE = TYPE;
    }

    public  void setGENDER(int GENDER){this.GENDER = GENDER;}

    public  void setAVATAR(Bitmap avatar){this.avatar = avatar;}


    public String getNAME() {
        return NAME;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public String getBIRTHDAY() {
        return BIRTHDAY;
    }

    public String getPHONE() {
        return PHONE;
    }


    public String getIMG() {
        return IMG;
    }

    public int getTYPE() { return TYPE; }

    public String getUID() {
        return UID;
    }

    public int getGENDER() {return GENDER;}

    public Bitmap getAVATAR() {return avatar;}

    @Override
    public String toString() {
        return "User{" +
                ", NAME='" + NAME + '\'' +
                ", EMAIL='" + EMAIL + '\'' +
                ", PHONE='" + PHONE + '\'' +
                 ", TYPE=" + TYPE +
                '}';
    }

   /* public static void addUserToFirestore(User user) {
        // Get info from Authentication
        FirebaseUser userInfo = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(COLLECTION_NAME).document(userInfo.getUid()).set(user);
    }

    public static void updateUserProfileFirestore(User user)    {
        FirebaseUser userInfo = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Update one field, creating the document if it does not already exist.
        Map<String, Object> data = new HashMap<>();
        data.put("name", user.getNAME());
        data.put("gender", user.getGENDER());
        data.put("phone", user.getPHONE());
        data.put("dob", user.getDOB());

        db.collection(COLLECTION_NAME).document(userInfo.getUid())
                .set(data, SetOptions.merge());

//        db.collection(COLLECTION_NAME).document(userInfo.getUid()).set(user);
    }


    public static void updateUserProfileAuthentication(String fullname, Uri uri)   {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null) return;


        UserProfileChangeRequest profileUpdates;

        if(uri != null) {
            profileUpdates = new UserProfileChangeRequest.Builder()
                    .setDisplayName(fullname)
                    .setPhotoUri(uri)
                    .build();
        }
        else    {
            profileUpdates = new UserProfileChangeRequest.Builder()
                    .setDisplayName(fullname)
                    .build();
        }


        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            System.out.println("Thanh cong");
                        }
                        else    {
                            System.out.println("That bai");
                        }
                    }
                });
    }*/
}

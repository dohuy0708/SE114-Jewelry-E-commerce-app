package com.example.jewelryecommerceapp.Models;

public class Catalogue {
    public static final String COLLECTION_NAME = "Catalogue";
    private String TypeProdID;    /*Ma loai san pham*/
    private String TypeProName;   /*Ten loai san pham: Nhan, day chuyen, vong tay, ...*/
    public int TypeProIcon;          /*Icon*/

    public Catalogue(){

    }

    public String getTypeProdID(){
        return TypeProdID;
    }
    public void setTypeProID(String TypeProID){
        this.TypeProdID=TypeProID;
    }

    public String getTypeProName(){
        return TypeProName;
    }
    public void setTypeProName(String TypeProName){
        this.TypeProName=TypeProName;
    }

    public int getTypeProIcon(){
        return TypeProIcon;
    }

    public void setTypeProIcon(int TypeProIcon) {
        this.TypeProIcon=TypeProIcon;
    }

    public Catalogue(String TypeProID, String TypeProName, int TypeProIcon) {
        this.TypeProdID=TypeProID;
        this.TypeProName=TypeProName;
        this.TypeProIcon=TypeProIcon;
    }

}

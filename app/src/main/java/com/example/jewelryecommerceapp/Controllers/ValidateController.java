package com.example.jewelryecommerceapp.Controllers;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateController {
    public static final String EMAIL_VERIFICATION = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile(EMAIL_VERIFICATION, Pattern.CASE_INSENSITIVE);
    public static final String ERROR_CODE1 = "Vui lòng nhập đầy đủ thông tin!";
    public static final String ERROR_CODE2 = "Email không hợp lệ, Vui lòng nhập lại Email!";
    public static final String ERROR_CODE3 = "Mật khẩu không hợp lệ, Vui lòng nhập lại mật khẩu!";
    public static final String ERROR_CODE4 = "Email or Password is incorrect. Please re-enter";
    public static final String ERROR_CODE5 = "Email may not exist. Please check again";
    public static final String ERROR_CODE6 = "Current password is incorrect";
    public static final String ERROR_CODE7 = "The new password does not match the old password";
    public static final String ERROR_CODE8 = "A few things have been changed recently. " +
            "Please check again";
    public static final String ERROR_CODE9 = "You must pick a date";
    public static final String ERROR_CODE10 = "Phone number at least 10 digits";

    public static final String SUCCESS_CODE1 = "Submitted successfully! Please check your email";
    public static final String SUCCESS_CODE2 = "Your password has been updated";


    // kiem tra xem cac gia tri Input dau vao co hop le khong. Ket qua cua ham sẽ tra ve 1 nêu hon le.
    // Trả về một chuỗi thông báo lỗi nếu khng hợp lệ


    // Kiểm tra xem đã đăng nhập  trước đó chưa chưa
    public static boolean isLoginBefore()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null)
        {
            return false;
        }
        else
            return true;
    }
    public static String isValidate(String name,String SDT, String email, String pass )
    {
        String result ="1";
        // kiem tra có ô nào bị bỏ trông không
        if (isFieldEmpty(name))
        {
                result = ERROR_CODE1;
                return result;
        }
        if(isFieldEmpty(email))
        {
            result = ERROR_CODE1;
            return result;
        }
        if(isFieldEmpty(SDT))
        {
            result = ERROR_CODE1;
            return result;
        }
        if(isFieldEmpty(pass))
        {
            result = ERROR_CODE1;
            return result;
        }
        //Kiem tra xem email co dung form khong
        if(!isEmail(email))
        {
            result = ERROR_CODE2 ;
            return result;
        }
        if(!isPassword(pass))
        {
            result = ERROR_CODE3;
            return result;
        }

        // nếu tất cả hớp lệ thì trả về "1"
        return result;
    }







    public static boolean isEmail(String emailStr) {
       /* Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();*/
        return true;
    }
    public static boolean isFieldEmpty(String field)    {
        return field.equals("");
    }
    public static boolean isPassword(String pw) {
        return (checkMin(pw, 8)/* &&
                checkOneLetterLowercaseInString(pw) &&
                checkOneLetterUppercaseInString(pw) &&
                checkOneLetterNumberInString(pw)*/) ? true : false;
    }
    public static boolean checkMin(String str, int minLength)  {
        return str.length() >= minLength ? true : false;
    }


    public static boolean checkOneLetterLowercaseInString(String str)    {
        for (int i=0;i<str.length();i++)    {
            char character = str.charAt(i);
            int ascii = (int) character;
            if(ascii >= 97 && ascii <= 122)
                return true;

        }
        return false;
    }


    public static boolean checkOneLetterUppercaseInString(String str)   {
        for (int i=0;i<str.length();i++)    {
            char character = str.charAt(i);
            int ascii = (int) character;
            if(ascii >= 65 && ascii <= 90)
                return true;

        }
        return false;
    }

    public static boolean checkOneLetterNumberInString(String str)   {
        for (int i=0;i<str.length();i++)    {
            char character = str.charAt(i);
            int ascii = (int) character;
            if(ascii >= 48 && ascii <= 57)
                return true;

        }
        return false;
    }




}

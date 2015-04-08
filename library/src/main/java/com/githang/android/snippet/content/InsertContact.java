/*
 * Time: 15-3-3 上午10:30
 * Project: AndroidSnippet
 */
package com.githang.android.snippet.content;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;

import static android.provider.ContactsContract.Intents.Insert;

/**
 * 插入联系人
 *
 * @author Haohang Huang (645079761@qq.com)
 * @version 0.3.1
 */
public class InsertContact {
    private Intent mIntent;

    public InsertContact() {
        mIntent = new Intent(Intent.ACTION_INSERT);
        mIntent.setType(ContactsContract.Contacts.CONTENT_TYPE);
    }

    public InsertContact name(String name) {
        mIntent.putExtra(Insert.NAME, name);
        return this;
    }

    public InsertContact phoneticName(String phoneticName) {
        mIntent.putExtra(Insert.PHONETIC_NAME, phoneticName);
        return this;
    }

    public InsertContact company(String company) {
        mIntent.putExtra(Insert.COMPANY, company);
        return this;
    }

    public InsertContact jobTitle(String jobTitle) {
        mIntent.putExtra(Insert.JOB_TITLE, jobTitle);
        return this;
    }

    public InsertContact notes(String notes) {
        mIntent.putExtra(Insert.NOTES, notes);
        return this;
    }

    public InsertContact phone(String phone) {
        mIntent.putExtra(Insert.PHONE, phone);
        return this;
    }

    /**
     * The extra field for the contact phone number type.
     *
     * @param phoneType an integer value from
     *                  {@link android.provider.ContactsContract.CommonDataKinds.Phone}.
     * @return InsertContact
     */
    public InsertContact phoneType(int phoneType) {
        mIntent.putExtra(Insert.PHONE_TYPE, phoneType);
        return this;
    }

    public InsertContact phoneType(String phoneType) {
        mIntent.putExtra(Insert.PHONE_TYPE, phoneType);
        return this;
    }

    public InsertContact phoneIsPrimary(boolean phoneIsPrimary) {
        mIntent.putExtra(Insert.PHONE_ISPRIMARY, phoneIsPrimary);
        return this;
    }

    public InsertContact secondaryPhone(String secondaryPhone) {
        mIntent.putExtra(Insert.SECONDARY_PHONE, secondaryPhone);
        return this;
    }

    /**
     * The extra field for the contact phone number type.
     *
     * @param secondaryPhoneType an integer value from
     *                           {@link android.provider.ContactsContract.CommonDataKinds.Phone}.
     * @return InsertContact
     */
    public InsertContact secondaryPhoneType(int secondaryPhoneType) {
        mIntent.putExtra(Insert.SECONDARY_PHONE_TYPE, secondaryPhoneType);
        return this;
    }

    public InsertContact secondaryPhoneType(String secondaryPhoneType) {
        mIntent.putExtra(Insert.SECONDARY_PHONE_TYPE, secondaryPhoneType);
        return this;
    }

    public InsertContact tertiaryPhone(String tertiaryPhone) {
        mIntent.putExtra(Insert.TERTIARY_EMAIL, tertiaryPhone);
        return this;
    }

    /**
     * The extra field for the contact phone number type.
     *
     * @param tertiaryPhoneType an integer value from
     *                          {@link android.provider.ContactsContract.CommonDataKinds.Phone}.
     * @return InsertContact
     */
    public InsertContact tertiaryPhoneType(int tertiaryPhoneType) {
        mIntent.putExtra(Insert.TERTIARY_PHONE_TYPE, tertiaryPhoneType);
        return this;
    }

    public InsertContact tertiaryPhoneType(String tertiaryPhoneType) {
        mIntent.putExtra(Insert.TERTIARY_PHONE_TYPE, tertiaryPhoneType);
        return this;
    }

    public InsertContact email(String email) {
        mIntent.putExtra(Insert.EMAIL, email);
        return this;
    }

    /**
     * The extra field for the contact phone number type.
     *
     * @param emailType an integer value from
     *                  {@link android.provider.ContactsContract.CommonDataKinds.Email}.
     * @return InsertContact
     */
    public InsertContact emailType(int emailType) {
        mIntent.putExtra(Insert.EMAIL_TYPE, emailType);
        return this;
    }

    public InsertContact emailType(String emailType) {
        mIntent.putExtra(Insert.EMAIL_TYPE, emailType);
        return this;
    }

    public InsertContact emailIsPrimary(boolean emailIsPrimary) {
        mIntent.putExtra(Insert.EMAIL_ISPRIMARY, emailIsPrimary);
        return this;
    }

    public InsertContact secondaryEmail(String secondaryEmail) {
        mIntent.putExtra(Insert.SECONDARY_EMAIL, secondaryEmail);
        return this;
    }

    /**
     * The extra field for the contact phone number type.
     *
     * @param secondaryEmailType an integer value from
     *                           {@link android.provider.ContactsContract.CommonDataKinds.Email}.
     * @return InsertContact
     */
    public InsertContact secondaryEmailType(int secondaryEmailType) {
        mIntent.putExtra(Insert.SECONDARY_EMAIL_TYPE, secondaryEmailType);
        return this;
    }

    public InsertContact secondaryEmailType(String secondaryEmailType) {
        mIntent.putExtra(Insert.SECONDARY_EMAIL_TYPE, secondaryEmailType);
        return this;
    }

    public InsertContact tertiaryEmail(String tertiaryEmail) {
        mIntent.putExtra(Insert.TERTIARY_EMAIL, tertiaryEmail);
        return this;
    }

    /**
     * The extra field for the contact phone number type.
     *
     * @param tertiaryEmailType an integer value from
     *                          {@link android.provider.ContactsContract.CommonDataKinds.Email}.
     * @return InsertContact
     */
    public InsertContact tertiaryEmailType(int tertiaryEmailType) {
        mIntent.putExtra(Insert.TERTIARY_EMAIL_TYPE, tertiaryEmailType);
        return this;
    }

    public InsertContact tertiaryEmailType(String tertiaryEmailType) {
        mIntent.putExtra(Insert.TERTIARY_EMAIL_TYPE, tertiaryEmailType);
        return this;
    }

    public InsertContact postal(String postal) {
        mIntent.putExtra(Insert.POSTAL, postal);
        return this;
    }
    /**
     * The extra field for the contact phone number type.
     *
     * @param postalType an integer value from
     *                          {@link android.provider.ContactsContract.CommonDataKinds.StructuredPostal}.
     * @return InsertContact
     */
    public InsertContact postalType(int postalType) {
        mIntent.putExtra(Insert.POSTAL_TYPE, postalType);
        return this;
    }

    public InsertContact postalType(String postalType) {
        mIntent.putExtra(Insert.POSTAL_TYPE, postalType);
        return this;
    }

    public InsertContact postalIsPrimary(boolean postalIsPrimary) {
        mIntent.putExtra(Insert.POSTAL_ISPRIMARY, postalIsPrimary);
        return this;
    }

    public InsertContact imHandler(String imHandler) {
        mIntent.putExtra(Insert.IM_HANDLE, imHandler);
        return this;
    }

    public InsertContact imProtocol(String imProtocol) {
        mIntent.putExtra(Insert.IM_PROTOCOL, imProtocol);
        return this;
    }

    public InsertContact imIsPrimary(boolean imIsPrimary) {
        mIntent.putExtra(Insert.IM_ISPRIMARY, imIsPrimary);
        return this;
    }

    /**
     * 打开插入联系人的界面并返回结果。
     *
     * @param activity    接收返回结果的界面
     * @param requestCode 请求码
     */
    public void showForResult(Activity activity, int requestCode) {
        activity.startActivityForResult(mIntent, requestCode);
    }

    /**
     * 打开插入联系人的界面
     *
     * @param context 上下文对象
     */
    public void show(Context context) {
        context.startActivity(mIntent);
    }
}

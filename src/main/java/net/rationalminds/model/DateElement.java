/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rationalminds.model;

/**
 *
 * @author Vaibhav Singh
 */
public class DateElement {

    String data;
    int tokenNum;
    int fragments;
    boolean isAlphaNumeric;
    int aphaNumericType;
    int startPos;
    int endPos;
    boolean hasAmPm = false;
    String timeFragment = null;
    String dateFragment = null;

    public DateElement(String data, int tokenNum, int fragments) {
        this.data = data;
        this.tokenNum = tokenNum;
        this.fragments = fragments;
    }

    public DateElement(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getTokenNum() {
        return tokenNum;
    }

    public void setTokenNum(int tokenNum) {
        this.tokenNum = tokenNum;
    }

    public int getFragments() {
        return fragments;
    }

    public void setFragments(int type) {
        this.fragments = fragments;
    }

    public boolean isIsAlphaNumeric() {
        return isAlphaNumeric;
    }

    public void setIsAlphaNumeric(boolean isAlphaNumeric) {
        this.isAlphaNumeric = isAlphaNumeric;
    }

    public int getAphaNumericType() {
        return aphaNumericType;
    }

    public void setAphaNumericType(int aphaNumericType) {
        this.aphaNumericType = aphaNumericType;
    }

    public int getStartPos() {
        return startPos;
    }

    public void setStartPos(int startPos) {
        this.startPos = startPos;
    }

    public int getEndPos() {
        return endPos;
    }

    public void setEndPos(int endPos) {
        this.endPos = endPos;
    }

    public boolean isHasAmPm() {
        return hasAmPm;
    }

    public void setHasAmPm(boolean hasAmPm) {
        this.hasAmPm = hasAmPm;
    }

    public String getTimeFragment() {
        return timeFragment;
    }

    public void setTimeFragment(String timeFragment) {
        this.timeFragment = timeFragment;
    }

    public String getDateFragment() {
        if (timeFragment == null) {
            return data;
        } else {
            return dateFragment;
        }
    }

    public void setDateFragment(String dateFragment) {
        this.dateFragment = dateFragment;
    }

    @Override
    public String toString() {
        return "DateElement{" + "data=" + data + ", tokenNum=" + tokenNum + ", fragments=" + fragments + ", isAlphaNumeric=" + isAlphaNumeric + ", aphaNumericType=" + aphaNumericType + ", startPos=" + startPos + ", endPos=" + endPos + ", hasAmPm=" + hasAmPm + ", timeFragment=" + timeFragment + ", dateFragment=" + dateFragment + '}';
    }

}

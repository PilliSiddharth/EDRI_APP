package com.example.myapplication9;

import java.util.ArrayList;

public class UserRequest {

    private ArrayList<String> collateral_damage;
    private ArrayList<String> zone_factor;
    private ArrayList<String> ground_shaking;
    private String spectre_shape;
    private ArrayList<String> importance_i;
    private String fsi;
    private boolean site1, site2, site3, soil1, soil2, arch1,arch2, strc1, strc2, strc3, con1, con2, con3, lossSite1, lossSoil1, lossSoil2;


    public boolean getSite1() {
        return site1;
    }

    public void setSite1(boolean site1) {
        this.site1 = site1;
    }

    public boolean getSite2() {
        return site2;
    }

    public void setSite2(boolean site2) {
        this.site2 = site2;
    }

    public boolean getSite3() {
        return site3;
    }

    public void setSite3(boolean site3) {
        this.site3 = site3;
    }

    public boolean getSoil1() {
        return soil1;
    }

    public void setSoil1(boolean soil1) {
        this.soil1 = soil1;
    }

    public boolean getSoil2() {
        return soil2;
    }

    public void setSoil2(boolean soil2) {
        this.soil2 = soil2;
    }

    public boolean getArch1() {
        return arch1;
    }

    public void setArch1(boolean arch1) {
        this.arch1 = arch1;
    }

    public boolean getArch2() {
        return arch2;
    }

    public void setArch2(boolean arch2) {
        this.arch2 = arch2;
    }

    public boolean getStrc1() {
        return strc1;
    }

    public void setStrc1(boolean strc1) {
        this.strc1 = strc1;
    }

    public boolean getStrc2() {
        return strc2;
    }

    public void setStrc2(boolean strc2) {
        this.strc2 = strc2;
    }

    public boolean getStrc3() {
        return strc3;
    }

    public void setStrc3(boolean strc3) {
        this.strc3 = strc3;
    }

    public boolean getCon1() {
        return con1;
    }

    public void setCon1(boolean con1) {
        this.con1 = con1;
    }

    public boolean getCon2() {
        return con2;
    }

    public void setCon2(boolean con2) {
        this.con2 = con2;
    }

    public boolean getCon3() {
        return con3;
    }

    public void setCon3(boolean con3) {
        this.con3 = con3;
    }

    public ArrayList<String> getCollateral_damage() {
        return collateral_damage;
    }

    public void setCollateral_damage(ArrayList<String> collateral_damage) {
        this.collateral_damage = collateral_damage;
    }

    public ArrayList<String> getZone_factor() {
        return zone_factor;
    }

    public void setZone_factor(ArrayList<String> zone_factor) {
        this.zone_factor = zone_factor;
    }

    public ArrayList<String> getGround_shaking() {
        return ground_shaking;
    }

    public void setGround_shaking(ArrayList<String> ground_shaking) {
        this.ground_shaking = ground_shaking;
    }

    public String getSpectre_shape() {
        return spectre_shape;
    }

    public void setSpectre_shape(String spectre_shape) {
        this.spectre_shape = spectre_shape;
    }

    public ArrayList<String> getImportance_i() {
        return importance_i;
    }

    public void setImportance_i(ArrayList<String> importance_i) {
        this.importance_i = importance_i;
    }

    public String getFsi() {
        return fsi;
    }

    public void setFsi(String fsi) {
        this.fsi = fsi;
    }

    public boolean getLossSite1() {
        return lossSite1;
    }

    public void setLossSite1(boolean lossSite1) {
        this.lossSite1 = lossSite1;
    }

    public boolean getLossSoil1() {
        return lossSoil1;
    }

    public void setLossSoil1(boolean lossSoil1) {
        this.lossSoil1 = lossSoil1;
    }

    public boolean getLossSoil2() {
        return lossSoil2;
    }

    public void setLossSoil2(boolean lossSoil2) {
        this.lossSoil2 = lossSoil2;
    }

    //    public String getLife_factor() {
//        return life_factor;
//    }
//
//    public void setLife_factor(String life_factor) {
//        this.life_factor = life_factor;
//
//    }
}

//    public String getLoss_factor() {
//        return loss_factor;
//    }
//
//    public void setLoss_factor(String loss_factor) {
//        this.loss_factor = loss_factor;
//    }
//}

package com.xiong.dandan.wudd.net.response;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 用户登录信息
 * Created by xionglh on 2017/2/22.
 */
public class UserInfo implements  Serializable {

    private static final long serialVersionUID = 1L;
    private String id;
    private String createDate;
    private String createUser;
    private String memo;
    private String lastUpdateUser;
    private String lastUpdateDate;
    private String version;
    private String recordStatus;
    private String loginName;
    private String loginPassword;
    private String tradePassword;
    private String credentialsType;
    private String credentialsCode;
    private String custName;
    private String custCode;
    private String birthday;
    private String custLevel;
    private String custGender;
    private String custSource;
    private String custType;
    private String natvicePlaceProvince;
    private String natvicePlaceCity;
    private String natvicePlaceCounty;
    private String natvicePlaceArea;
    private String communAddress;
    private String mobile;
    private String email;
    private String portraitPath;
    private String realNameAuthCount;
    private String isLumper;
    private String msgOnOff;
    private String enableStatus;
    /**
     * 邀请码 *
     */
    private String inviteCode;
    private String integral;
    private String qrCodePath;
    private String inviteOriginId;
    private String queryPermission;
    private String spreadLevel;
    private String isRecommend;

    private String hasPermission;//是否有资格购买新手宝

    private String custStatus;//unlogin 未登录 ableToBuy 已登录 and 具备新手宝购买资格unableToBuy 已登录 and 不具备新手宝购买资格 and 从未投资过新手宝 的用户earn 适用于已购买过新手宝，但新手宝还未到期的用户finish 适用于已购买过新手宝，且新手宝已到期的用户


    private String custKind;

    public String getCustKind() {
        return custKind;
    }

    public void setCustKind(String custKind) {
        this.custKind = custKind;
    }

    public String getCustStatus() {
        return custStatus;
    }

    public void setCustStatus(String custStatus) {
        this.custStatus = custStatus;
    }

    private ArrayList<BankInfo> bankCardInfoEntitys;

    public ArrayList<BankInfo> getBankCardInfoEntitys() {
        return bankCardInfoEntitys;
    }

    public void setBankCardInfoEntitys(ArrayList<BankInfo> bankCardInfoEntitys) {
        this.bankCardInfoEntitys = bankCardInfoEntitys;
    }

    public String getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(String isRecommend) {
        this.isRecommend = isRecommend;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getLastUpdateUser() {
        return lastUpdateUser;
    }

    public void setLastUpdateUser(String lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser;
    }

    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getTradePassword() {
        return tradePassword;
    }

    public void setTradePassword(String tradePassword) {
        this.tradePassword = tradePassword;
    }

    public String getCredentialsType() {
        return credentialsType;
    }

    public void setCredentialsType(String credentialsType) {
        this.credentialsType = credentialsType;
    }

    public String getCredentialsCode() {
        return credentialsCode;
    }

    public void setCredentialsCode(String credentialsCode) {
        this.credentialsCode = credentialsCode;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustCode() {
        return custCode;
    }

    public void setCustCode(String custCode) {
        this.custCode = custCode;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCustLevel() {
        return custLevel;
    }

    public void setCustLevel(String custLevel) {
        this.custLevel = custLevel;
    }

    public String getCustGender() {
        return custGender;
    }

    public void setCustGender(String custGender) {
        this.custGender = custGender;
    }

    public String getCustSource() {
        return custSource;
    }

    public void setCustSource(String custSource) {
        this.custSource = custSource;
    }

    public String getCustType() {
        return custType;
    }

    public void setCustType(String custType) {
        this.custType = custType;
    }

    public String getNatvicePlaceProvince() {
        return natvicePlaceProvince;
    }

    public void setNatvicePlaceProvince(String natvicePlaceProvince) {
        this.natvicePlaceProvince = natvicePlaceProvince;
    }

    public String getNatvicePlaceCity() {
        return natvicePlaceCity;
    }

    public void setNatvicePlaceCity(String natvicePlaceCity) {
        this.natvicePlaceCity = natvicePlaceCity;
    }

    public String getNatvicePlaceCounty() {
        return natvicePlaceCounty;
    }

    public void setNatvicePlaceCounty(String natvicePlaceCounty) {
        this.natvicePlaceCounty = natvicePlaceCounty;
    }

    public String getNatvicePlaceArea() {
        return natvicePlaceArea;
    }

    public void setNatvicePlaceArea(String natvicePlaceArea) {
        this.natvicePlaceArea = natvicePlaceArea;
    }

    public String getCommunAddress() {
        return communAddress;
    }

    public void setCommunAddress(String communAddress) {
        this.communAddress = communAddress;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPortraitPath() {
        return portraitPath;
    }

    public void setPortraitPath(String portraitPath) {
        this.portraitPath = portraitPath;
    }

    public String getRealNameAuthCount() {
        return realNameAuthCount;
    }

    public void setRealNameAuthCount(String realNameAuthCount) {
        this.realNameAuthCount = realNameAuthCount;
    }

    public String getIsLumper() {
        return isLumper;
    }

    public void setIsLumper(String isLumper) {
        this.isLumper = isLumper;
    }

    public String getMsgOnOff() {
        return msgOnOff;
    }

    public void setMsgOnOff(String msgOnOff) {
        this.msgOnOff = msgOnOff;
    }

    public String getEnableStatus() {
        return enableStatus;
    }

    public void setEnableStatus(String enableStatus) {
        this.enableStatus = enableStatus;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }

    public String getQrCodePath() {
        return qrCodePath;
    }

    public void setQrCodePath(String qrCodePath) {
        this.qrCodePath = qrCodePath;
    }

    public String getInviteOriginId() {
        return inviteOriginId;
    }

    public void setInviteOriginId(String inviteOriginId) {
        this.inviteOriginId = inviteOriginId;
    }

    public String getQueryPermission() {
        return queryPermission;
    }

    public void setQueryPermission(String queryPermission) {
        this.queryPermission = queryPermission;
    }

    public String getSpreadLevel() {
        return spreadLevel;
    }

    public void setSpreadLevel(String spreadLevel) {
        this.spreadLevel = spreadLevel;
    }

    public String getHasPermission() {
        return hasPermission;
    }

    public void setHasPermission(String hasPermission) {
        this.hasPermission = hasPermission;
    }

    public class ContanctInfo  implements Serializable {
        private String id;
        private String createDate;
        private String createUser;
        private String memo;
        private String lastUpdateUser;
        private String lastUpdateDate;
        private String version;
        private String recordStatus;
        private String contactName;
        private String relationType;
        private String contanctTelePhone;
        private String descr;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getCreateUser() {
            return createUser;
        }

        public void setCreateUser(String createUser) {
            this.createUser = createUser;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public String getLastUpdateUser() {
            return lastUpdateUser;
        }

        public void setLastUpdateUser(String lastUpdateUser) {
            this.lastUpdateUser = lastUpdateUser;
        }

        public String getLastUpdateDate() {
            return lastUpdateDate;
        }

        public void setLastUpdateDate(String lastUpdateDate) {
            this.lastUpdateDate = lastUpdateDate;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getRecordStatus() {
            return recordStatus;
        }

        public void setRecordStatus(String recordStatus) {
            this.recordStatus = recordStatus;
        }

        public String getContactName() {
            return contactName;
        }

        public void setContactName(String contactName) {
            this.contactName = contactName;
        }

        public String getRelationType() {
            return relationType;
        }

        public void setRelationType(String relationType) {
            this.relationType = relationType;
        }

        public String getContanctTelePhone() {
            return contanctTelePhone;
        }

        public void setContanctTelePhone(String contanctTelePhone) {
            this.contanctTelePhone = contanctTelePhone;
        }

        public String getDescr() {
            return descr;
        }

        public void setDescr(String descr) {
            this.descr = descr;
        }
    }


}

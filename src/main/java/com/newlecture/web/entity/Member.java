package com.newlecture.web.entity;

public class Member {
   private int id;
   private String nicName;
   private String pwd;
   private String name;
   private String gender;
   private String birthday;    
   private String phone;
   private String email;
   private String regdate;
   
   public Member() {
      // TODO Auto-generated constructor stub
   }

   /**
    * @param id
    * @param nicName
    * @param pwd
    * @param name
    * @param gender
    * @param birthday
    * @param phone
    * @param email
    * @param regdate
    */
   public Member(int id, String nicName, String pwd, String name, String gender, String birthday, String phone,
         String email, String regdate) {
      super();
      this.id = id;
      this.nicName = nicName;
      this.pwd = pwd;
      this.name = name;
      this.gender = gender;
      this.birthday = birthday;
      this.phone = phone;
      this.email = email;
      this.regdate = regdate;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getNicName() {
      return nicName;
   }

   public void setNicName(String nicName) {
      this.nicName = nicName;
   }

   public String getPwd() {
      return pwd;
   }

   public void setPwd(String pwd) {
      this.pwd = pwd;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getGender() {
      return gender;
   }

   public void setGender(String gender) {
      this.gender = gender;
   }

   public String getBirthday() {
      return birthday;
   }

   public void setBirthday(String birthday) {
      this.birthday = birthday;
   }

   public String getPhone() {
      return phone;
   }

   public void setPhone(String phone) {
      this.phone = phone;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getRegdate() {
      return regdate;
   }

   public void setRegdate(String regdate) {
      this.regdate = regdate;
   }

   @Override
   public String toString() {
      return "Member [id=" + id + ", nicName=" + nicName + ", pwd=" + pwd + ", name=" + name + ", gender=" + gender
            + ", birthday=" + birthday + ", phone=" + phone + ", email=" + email + ", regdate=" + regdate + "]";
   }
   
}
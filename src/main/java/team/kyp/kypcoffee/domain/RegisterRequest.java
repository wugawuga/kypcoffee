package team.kyp.kypcoffee.domain;

import lombok.Data;

import java.util.Date;

@Data
public class RegisterRequest { //회원 가입시 입력할 정보를 담기 위한 클래스
    private int no;
    private String id;
    private String email;
    private String pw;
    private String confirmPw;
    private String name;
    private String phone;
    private String tel;
    private Date birth;
    private String address;
    private int emailyn;
    private int point;
    private int type;
    public boolean isPasswordEqualToConfirmPassword() {
        return pw.equals(confirmPw);
    }


    public int getNo() {return no;}

    public void setNo(int no) {this.no = no;}

    public String getId() {return id;}

    public void setId(String id) {this.id = id;}

    public String getPw() {return pw;}

    public void setPw(String pw) {this.pw = pw;}

    public String getConfirmPw() {return confirmPw;}

    public void setConfirmPw(String confirmPw) {this.confirmPw = confirmPw;}

    public String getPhone() {return phone;}

    public void setPhone(String phone) {this.phone = phone;}

    public String getTel() {return tel;}

    public void setTel(String tel) {this.tel = tel;}

    public Date getBirth() {return birth;}

    public void setBirth(Date birth) {this.birth = birth;}

    public String getAddress() {return address;}

    public void setAddress(String address) {this.address = address;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public int getEmailyn() {return emailyn;}

    public void setEmailyn(int emailyn) {this.emailyn = emailyn;}

    public int getPoint() {return point;}

    public void setPoint(int point) {this.point = point;}

    public int getType() {return type;}

    public void setType(int type) {this.type = type;}


    public String getConfirmPassword() {
        return confirmPw;
    }

    public void setConfirmPassword(String confirmPw) {
        this.confirmPw = confirmPw;
    }

}

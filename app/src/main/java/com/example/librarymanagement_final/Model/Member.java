package com.example.librarymanagement_final.Model;

public class Member {
    private int memberId;
    private String name;
    private String membershipDate;

    public Member(int memberId, String name, String membershipDate) {
        this.memberId = memberId;
        this.name = name;
        this.membershipDate = membershipDate;
    }

    public int getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public String getMembershipDate() {
        return membershipDate;
    }
}

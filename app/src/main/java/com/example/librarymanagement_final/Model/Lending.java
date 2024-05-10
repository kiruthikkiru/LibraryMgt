package com.example.librarymanagement_final.Model;

public class Lending {
    private int lendingId;
    private int bookId;
    private int memberId;
    private String borrowedDate;
    private String returnDate;

    public Lending(int lendingId, int bookId, int memberId, String borrowedDate, String returnDate) {
        this.lendingId = lendingId;
        this.bookId = bookId;
        this.memberId = memberId;
        this.borrowedDate = borrowedDate;
        this.returnDate = returnDate;
    }

    public int getLendingId() {
        return lendingId;
    }

    public int getBookId() {
        return bookId;
    }

    public int getMemberId() {
        return memberId;
    }

    public String getBorrowedDate() {
        return borrowedDate;
    }

    public String getReturnDate() {
        return returnDate;
    }
}

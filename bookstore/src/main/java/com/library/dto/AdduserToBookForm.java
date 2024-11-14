package com.library.dto;

import java.util.List;
public class AdduserToBookForm {
        private Integer bookId;
        private List<Integer> userIds;
        public Integer getBookId() {
                return bookId;
        }
        public void setBookId(Integer bookId) {
                this.bookId = bookId;
        }
        public List<Integer> getUserIds() {
                return userIds;
        }
        public void setUserIds(List<Integer> userIds) {
                this.userIds = userIds;
        }

    @Override
    public String toString() {
        return "Book Id: " + bookId + "User Ids:" + userIds;
    }

        

}

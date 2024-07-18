package cst8288.project.fwrp.model;

import java.time.LocalDateTime;

public class Notification {
    private Long id;
    private User user;
    private Item item;
    private String message;
    private CommMethodType commMethod;
    private LocalDateTime sentTime;

    public Notification(Builder builder) {
        this.user = builder.user;
        this.item = builder.item;
        this.message = builder.message;
        this.commMethod = builder.commMethod;
        this.sentTime = builder.sentTime;
    }
    public static class Builder {
        private User user;
        private Item item;
        private String message;
        private CommMethodType commMethod;
        private LocalDateTime sentTime;

        public Builder setUser(User user) {
            this.user = user;
            return this;
        }
        public Builder setItem(Item item) {
            this.item = item;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }
        public Builder setCommMethod(CommMethodType commMethod) {
            this.commMethod = commMethod;
            return this;
        }
        public Builder setSentTime(LocalDateTime sentTime) {
            this.sentTime = sentTime;
            return this;
        }
        public Notification build() {
            return new Notification(this);
        }
    }

    public User getUser() {
        return user;
    }

    public Item getItem() {
        return item;
    }

    public String getMessage() {
        return message;
    }

    public CommMethodType getCommMethod() {
        return commMethod;
    }

    public LocalDateTime getSentTime() {
        return sentTime;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "user=" + user +
                ", item=" + item +
                ", message='" + message + '\'' +
                ", commMethod=" + commMethod +
                ", sentTime=" + sentTime +
                '}';
    }
}

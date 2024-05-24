package com.amalitech.upskilling.week_one.lab_two.creational.builder;

class User {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String phone;

    private User(UserBuilder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.phone = builder.phone;
    }

    public static class UserBuilder {
        private final String firstName;
        private final String lastName;
        private String email;
        private String phone;

        public UserBuilder(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}

class BuilderPatternUserExample {
    public static void main(String[] args) {
        User user = new User.UserBuilder("John", "Doe")
                .email("john.doe@example.com")
                .phone("123-456-7890")
                .build();
        System.out.println(user);
    }
}

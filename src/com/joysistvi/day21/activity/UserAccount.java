package com.joysistvi.day21.activity;

public class UserAccount {

    // data hiding -> all data members are private
    private String firstName;
    private String middleName = "";   // optional field, so it defaults to empty instead of null
    private String lastName;
    private String address;
    private String email;
    private String password;

    private static final String EMAIL_REGEX =
            "^[A-Za-z0-9._%+-]+@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*\\.[A-Za-z]{2,}$";
            //source for regex https://gist.github.com/cgkio/7268045
    private static final int MIN_PASSWORD_LENGTH = 8;

    public UserAccount() {
    }

    // parameterized constructor
    // It calls the setters (through this) instead of assigning the fields directly,
    // so every validation rule still runs when the object is created.
    public UserAccount(String firstName, String middleName, String lastName,
                       String address, String email, String password) {
        this.setFirstName(firstName);
        this.setMiddleName(middleName);
        this.setLastName(lastName);
        this.setAddress(address);
        this.setEmail(email);
        this.setPassword(password);
    }

    // Getters
    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    // Setters (with validation)
    public void setFirstName(String firstName) {
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be empty.");
        }
        this.firstName = firstName.trim();
    }

    public void setMiddleName(String middleName) {
        this.middleName = (middleName == null) ? "" : middleName.trim();
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be empty.");
        }
        this.lastName = lastName.trim();
    }

    public void setAddress(String address) {
        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException("Address cannot be empty.");
        }
        this.address = address.trim();
    }

    public void setEmail(String email) {
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format.");
        }
        this.email = email.trim();
    }

    public void setPassword(String password) {
        String problem = getPasswordProblem(password);
        if (problem != null) {
            throw new IllegalArgumentException(problem);
        }
        this.password = password;
    }

    // Validation helpers
    public static boolean isValidEmail(String email) {
        return email != null && email.trim().matches(EMAIL_REGEX);
    }

    public static String getPasswordProblem(String password) {
        if (password == null || password.length() < MIN_PASSWORD_LENGTH) {
            return "Password must be at least " + MIN_PASSWORD_LENGTH + " characters long.";
        }
        if (password.contains(" ")) {
            return "Password must not contain spaces.";
        }
        boolean hasLetter = false;
        boolean hasDigit = false;
        for (char c : password.toCharArray()) {
            if (Character.isLetter(c)) {
                hasLetter = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            }
        }
        if (!hasLetter || !hasDigit) {
            return "Password must contain at least one letter and one number.";
        }
        return null;
    }

    public String getFullName() {
        if (middleName.isEmpty()) {
            return firstName + " " + lastName;
        }
        return firstName + " " + middleName + " " + lastName;
    }

    // String representation of the object.
    // The password is intentionally NOT included.
    @Override
    public String toString() {
        return "Full Name: " + getFullName() + "\n" +
                "Email: " + email + "\n" +
                "Address: " + address;
    }
}



public abstract class User {

    private String username;
    private String password;
    private String address;
    private String phoneNumber;

    public User(String username, String password, String address, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setUsername(String username){this.username = username;}

    public void setPassword(String password) {
        this.password = password;
    }
    public void updateProfile(String newUsername,String newPassword, String newAddress, String newPhoneNumber) {
       setUsername(newUsername);
        setPhoneNumber(newPhoneNumber);
        setAddress(newAddress);
        setPassword(newPassword);
        System.out.println("Profile updated successfully.");
    }

    public void displayProfile(){
        System.out.println("Username: " + getUsername());
        System.out.println("Address: " + getAddress());
        System.out.println("Phone: " + getPhoneNumber());
    }


}

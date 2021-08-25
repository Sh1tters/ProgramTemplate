package Model;

public final class UserModel {
    private String name;
    private Information information;

    public UserModel(String username, Information information) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Information getInformation() {
        return information;
    }

    public void setInformation(Information information) {
        this.information = information;
    }


}


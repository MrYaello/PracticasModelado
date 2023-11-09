package mx.unam.ciencias.chatApp;

public class Client {
    private String username = "";
    private ClientState state = ClientState.ACTIVE;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ClientState getState() {
        return state;
    }

    public void setState(ClientState state) {
        this.state = state;
    }
}

package mx.unam.ciencias.chatApp;

import java.util.Objects;

public class Client {
    private String username = "";
    private ClientState state = ClientState.ACTIVE;

    public Client() {}

    public Client(String username, ClientState state) {
        this.username = username;
        this.state = state;
    }

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Client client = (Client) obj;
        return Objects.equals(username, client.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}

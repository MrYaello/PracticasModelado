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

    public Client(String username, String state) {
        this.username = username;
        switch (state) {
            case "ACTIVE":
                this.state = ClientState.ACTIVE;
                break;
            case "AWAY":
                this.state = ClientState.AWAY;
                break;
            case "BUSY":
                this.state = ClientState.BUSY;
                break;
        }
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

    public void setState(String state) {
        switch (state) {
            case "ACTIVE":
                this.state = ClientState.ACTIVE;
                break;
            case "AWAY":
                this.state = ClientState.AWAY;
                break;
            case "BUSY":
                this.state = ClientState.BUSY;
                break;
        }
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

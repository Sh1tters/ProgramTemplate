package Handlers;

import com.google.gson.Gson;
import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.json.JSONObject;

import java.util.Objects;

import static com.mongodb.client.model.Filters.eq;


public class MongoDB {
    ConnectionString connectionString = new ConnectionString("mongodb+srv://User:User@cluster0.yjmz2.mongodb.net/Program?retryWrites=true&w=majority");
    MongoClient mongoClient = MongoClients.create(connectionString);
    MongoDatabase database = mongoClient.getDatabase("Program");
    MongoCollection collection = database.getCollection("User");

    public void Connect() throws Exception {

    }

    public String GetName(String username) {

        return GetName(username);
    }

    public String GetPassword(String username) {
        String password = "null";
        return GetPassword(password);
    }

    public boolean ValidationLogin(String username, String password) throws Exception {
        Object rawData = collection.find(eq("name", username)).first();//finds username and returns null if nothing found.
        /** check if object is null (name not found)*/
        if (rawData == null) {
            return false;
        } else {
            /** Convert java object to json object */
            String jsonInString = new Gson().toJson(rawData);

            JSONObject jsonData = new JSONObject(jsonInString);

            /** Get value from key */
            String user = jsonData.getString("name");
            String pass = jsonData.getString("password");
            if (Objects.equals(user, username) && Objects.equals(pass, password)) {
                return true;
            } else {
                return false;
            }
        }

    }

    public boolean ValidationSignUp(String username) throws Exception {
        if (collection.find(eq("name", username)).first() != null) {
            return false;
        } else {
            return true;
        }
    }

    public void SignNewUser(String username, String password) throws Exception {
        //TODO structure the document with tree structure somehow, dont use document, its shit
        /** Establish connection? */
        /** Structure for user document 
         *
         * NAME
         *      Name: NAME
         *      Password: PASSWORD
         *      SignedInDate: SIGNEDINDATE
         *      ProfileAvater: DEFAULT_LINK (...)
         *          SIZE: y,x
         *          .... (other important things for a clean image view)
         *      Friend Request: null
         *      Friends: null
         *      Email: null(OPTIONAL IN PROFILE SETTINGS)
         *      Blocked: null
         *
         *
         *
         */
    }

    public void Close() throws Exception {

    }

    public boolean IsConnected() throws Exception {
        return false;
    }
}

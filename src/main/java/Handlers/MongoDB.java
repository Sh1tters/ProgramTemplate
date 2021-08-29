package Handlers;

import com.google.gson.Gson;
import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import static com.mongodb.client.model.Filters.eq;


public class MongoDB {
    public static ConnectionString connectionString = new ConnectionString("CONNECTION URL STRING HERE");
    public static MongoClient mongoClient = MongoClients.create(connectionString);
    public static MongoDatabase database = mongoClient.getDatabase("DATABASE NAME HERE");
    public static MongoCollection collection = database.getCollection("COLLECTION NAME HERE");

    Handler handler = new Handler();

    public void Connect() throws Exception {

    }

    public String GetName(String username) {

        return GetName(username);
    }

    public static String getProfileAvatar(String username) {
        String ProfileAvatar = "";
        Object rawData = collection.find(eq("name", username)).first();//finds username and returns null if nothing found.

        /** Convert java object to json object */
        String jsonInString = new Gson().toJson(rawData);

        JSONObject jsonData = new JSONObject(jsonInString);

        /** Get value from key */
        ProfileAvatar = jsonData.getString("profileAvatar");

        return ProfileAvatar;
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

            /** Encrypt password and then check */
            password = Handler.toHexString(handler.PasswordEncryption(password));

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
        /** Get current date and time */
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        Document doc = new Document();
        doc.append("name", username);
        doc.append("password", password);
        doc.append("joined", now);
        doc.append("email", null);
        doc.append("profileAvatar", "https://t4.ftcdn.net/jpg/00/64/67/63/360_F_64676383_LdbmhiNM6Ypzb3FM4PPuFP9rHe7ri8Ju.jpg");
        /** display url image: https://stackoverflow.com/questions/13448368/trying-to-display-url-image-in-jframe */
        collection.insertOne(doc);
    }

    public void Close() throws Exception {

    }

    public boolean IsConnected() throws Exception {
        return false;
    }
}

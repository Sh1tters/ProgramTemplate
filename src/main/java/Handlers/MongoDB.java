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
    public static ConnectionString connectionString = new ConnectionString("mongodb+srv://User:User@cluster0.yjmz2.mongodb.net/Program?retryWrites=true&w=majority");
    public static MongoClient mongoClient = MongoClients.create(connectionString);
    public static MongoDatabase database = mongoClient.getDatabase("Program");
    public static MongoCollection collection = database.getCollection("User");

    Handler handler = new Handler();

    public void Connect() throws Exception {

    }

    public String GetName(String username) {

        return GetName(username);
    }

    public String GetPassword(String username) {
        String password = "null";
        return GetPassword(password);
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
        //TODO structure the document with tree structure somehow, dont use document, its shit
        // for now we keep the same shit way
        /** Get current date and time */
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        Document doc = new Document();
        doc.append("name", username);
        doc.append("password", password);
        doc.append("joined", now);
        doc.append("email", null);
        doc.append("profileAvatar", "https://image.pngaaa.com/909/2676909-middle.png");
        /** display url image: https://stackoverflow.com/questions/13448368/trying-to-display-url-image-in-jframe */
        collection.insertOne(doc);
    }

    public void Close() throws Exception {

    }

    public boolean IsConnected() throws Exception {
        return false;
    }
}

package dk.signtool.shadow.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dk.signtool.shadow.utils.GetUUID;

public class checkerUser {


    public static boolean isWhitelisted(String getWhitelistedUsers) throws Exception {
        String uuid = GetUUID.getUUID(getWhitelistedUsers);
        System.out.println("uuid " + uuid);
        JsonObject users = GetUsers.GetWhitelistedUsers();
        System.out.println("123333333333333333333333333333333333333333333333333333333333333333333333333333333333");
        JsonArray usersElement = users.get("hvidlist").getAsJsonArray();
        System.out.println("4444444444444444444444444444444444444444444444444444444444444444444444444");
        for (JsonElement jsonElement : usersElement) {
            System.out.println("-------------------------------------------------------------------------------------------------------------------");
            System.out.println("jsonElement " + jsonElement);
            if (jsonElement.getAsString().equals(uuid))
                return true;
        }
        return false;
    }


}

package me.duloxetina.FileHandler;
import me.duloxetina.nPunishment;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;

public class messages {
    private static FileConfiguration messages = null;
    private static File messagesFile = null;
    public static FileConfiguration get(){
        if(messages == null){
            reload();
        }
        return messages;
    }

    public static void reload(){
        if(messages == null){
            messagesFile = new File(nPunishment.get().getDataFolder(),"messages.yml");
        }
        messages = YamlConfiguration.loadConfiguration(messagesFile);
        Reader defConfigStream;
        try{
            defConfigStream = new InputStreamReader(nPunishment.get().getResource("messages.yml"),"UTF8");
            if(defConfigStream != null){
                YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
                messages.setDefaults(defConfig);
            }
        }catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }
    }

    public static void saveMessages(){
        try{
            messages.save(messagesFile);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void registerMessages(){
        messagesFile = new File(nPunishment.get().getDataFolder(),"messages.yml");
        if(!messagesFile.exists()){
            get().options().copyDefaults(true);
            saveMessages();
        }
    }
}

package mongojavaimport;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.BulkWriteOptions;
import com.mongodb.client.model.InsertOneModel;
import org.bson.Document;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
//        String dataFolderPath = "C:\\Users\\emili\\Documents\\M2\\big_data\\data_Twitter\\data";
//
//        // Mongo connection
//        MongoClient client = MongoClients.create("mongodb://localhost:27017");
//        MongoDatabase database = client.getDatabase("tweets");
//        MongoCollection<Document> coll = database.getCollection("tweets");
//
//        File dataFolder = new File(dataFolderPath);
//        if (dataFolder.exists() && dataFolder.isDirectory()) {
//            File[] jsonFiles = dataFolder.listFiles((dir, name) -> name.toLowerCase().endsWith(".json"));
//            if (jsonFiles != null) {
//                for (File jsonFile : jsonFiles) {
//                    try (BufferedReader br = new BufferedReader(new FileReader(jsonFile))) {
//
//                        List<InsertOneModel<Document>> docs = new ArrayList<>();
//                        String line;
//                        int count = 0;
//                        while ((line = br.readLine()) != null) {
//                            docs.add(new InsertOneModel<>(Document.parse(line)));
//                            count++;
//
//                            // Insérer par lots tous les 1000 documents
//                            if (count % 1000 == 0) {
//                                coll.bulkWrite(docs, new BulkWriteOptions().ordered(false));
//                                docs.clear();
//                            }
//                        }
//                        // Insérer les documents restants
//                        if (!docs.isEmpty()) {
//                            coll.bulkWrite(docs, new BulkWriteOptions().ordered(false));
//                        }
//                        System.out.println("Les objets JSON du fichier " + jsonFile.getName() + " ont été ajoutés en base de données.");
//                    } catch (Exception e) {
//                        System.out.println("Erreur lors de l'importation des objets JSON du fichier " + jsonFile.getName() + ": " + e.getMessage());
//                    }
//                }
//            } else {
//                System.out.println("Aucun fichier JSON trouvé dans le dossier de données.");
//            }
//        } else {
//            System.out.println("Le dossier de données n'existe pas ou n'est pas un répertoire.");
//        }


    }
}
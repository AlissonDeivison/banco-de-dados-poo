package com.br.mongo_java.mongo_java;

import model.Produto;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.function.Consumer;

import static com.mongodb.client.model.Updates.set;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;


public class App 
{
    public static void main( String[] args )
    {
        CodecRegistry pojoCodeRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
        		fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        
        MongoClient mongoClient = new MongoClient("localhost:27017",
        		MongoClientOptions.builder().codecRegistry(pojoCodeRegistry).build());
        
        MongoDatabase database = mongoClient.getDatabase("exemplo").withCodecRegistry(pojoCodeRegistry);
        
        MongoCollection<Produto> collection = database.getCollection("Produto", Produto.class);
        
        
        //Inserindo um objeto
        //collection.insertOne(new Produto(2, "Feijao", 7));
        
        //Atualizar um objeto
        //collection.updateOne(new Document ("_id",1), set("descricao","Arroz Parbolizado"));
        
        //Deletar um objeto
        collection.deleteOne(new Document("descricao", "Arroz Parbolizado"));
    }
}

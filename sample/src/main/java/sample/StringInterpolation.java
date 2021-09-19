package sample;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import lombok.Builder;

import java.io.*;
import java.util.HashMap;

public class StringInterpolation {

    @Builder
    static class Item {
        String id, name;
        Item(String id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    public static void main(String[] args) {
        try {
            // クラスの変数で式展開
            StringWriter sw = new StringWriter();
            MustacheFactory mf = new DefaultMustacheFactory();
            Mustache mustache = mf.compile(new StringReader("id:{{id}}、name:{{name}}"), "exampleClass");
            mustache.execute(
                    new PrintWriter(sw),
                    Item.builder().id("classId").name("className").build()
            ).flush();
            System.out.println(sw.toString());

            // Mapで式展開
            HashMap<String, String> scopes = new HashMap<String, String>();
            scopes.put("id", "mapId");
            scopes.put("name", "mapName");
            sw = new StringWriter();
            mf = new DefaultMustacheFactory();
            mustache = mf.compile(new StringReader("id:{{id}}、name:{{name}}"), "exampleMap");
            mustache.execute(new PrintWriter(sw), scopes).flush();
            System.out.println(sw.toString());
        } catch (IOException e) {
            System.out.println("IOException");
        }

    }
}

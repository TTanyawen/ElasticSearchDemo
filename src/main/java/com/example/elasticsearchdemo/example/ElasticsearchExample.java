//package com.example.elasticsearchdemo.example;
//
//import co.elastic.clients.elasticsearch.ElasticsearchClient;
//import co.elastic.clients.elasticsearch.core.*;
//import co.elastic.clients.elasticsearch.core.search.Hit;
//import co.elastic.clients.elasticsearch.indices.CreateIndexRequest;
//import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
//import co.elastic.clients.elasticsearch.indices.DeleteIndexRequest;
//import co.elastic.clients.elasticsearch.indices.DeleteIndexResponse;
//import co.elastic.clients.json.jackson.JacksonJsonpMapper;
//import co.elastic.clients.transport.ElasticsearchTransport;
//import co.elastic.clients.transport.rest_client.RestClientTransport;
//import org.apache.http.HttpHost;
//import org.apache.http.auth.AuthScope;
//import org.apache.http.auth.UsernamePasswordCredentials;
//import org.apache.http.client.CredentialsProvider;
//import org.apache.http.impl.client.BasicCredentialsProvider;
//import org.elasticsearch.client.RestClient;
//
//import java.io.IOException;
//import java.util.List;
//
//public class ElasticsearchExample {
//    public static void main(String[] args) {
//        // 创建一个凭证提供者（如果需要认证）
//        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
//        // 如果你的 Elasticsearch 需要用户名和密码认证，可以取消注释并设置相应的值
//        // credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("username", "password"));
//
//        // 创建 REST 客户端
//        RestClient restClient = RestClient.builder(
//                        new HttpHost("localhost", 9200))
//                .setHttpClientConfigCallback(httpClientBuilder ->
//                        httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider))
//                .build();
//
//        // 创建传输层
//        ElasticsearchTransport transport = new RestClientTransport(
//                restClient, new JacksonJsonpMapper());
//
//        // 创建 Elasticsearch 客户端
//        ElasticsearchClient client = new ElasticsearchClient(transport);
//
//        try {
//            // 创建索引
//            createIndex(client, "test_index");
//
//            // 添加文档
//            addDocument(client, "test_index", "1", "{\"name\": \"John Doe\", \"age\": 30}");
//
//            // 查询文档
//            searchDocuments(client, "test_index", "John");
//
//            // 删除文档
//            deleteDocument(client, "test_index", "1");
//
//            // 删除索引
//            deleteIndex(client, "test_index");
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                // 关闭传输层和 REST 客户端
//                transport.close();
//                restClient.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    // 创建索引
//    public static void createIndex(ElasticsearchClient client, String indexName) throws IOException {
//        CreateIndexRequest request = new CreateIndexRequest.Builder()
//                .index(indexName)
//                .build();
//        CreateIndexResponse response = client.indices().create(request);
//        if (response.acknowledged()) {
//            System.out.println("Index created: " + indexName);
//        }
//    }
//
//    // 添加文档
//    public static void addDocument(ElasticsearchClient client, String indexName, String id, String jsonDocument) throws IOException {
//        IndexRequest<String> request = new IndexRequest.Builder<String>()
//                .index(indexName)
//                .id(id)
//                .document(jsonDocument)
//                .build();
//        IndexResponse response = client.index(request);
//        System.out.println("Document added with ID: " + response.id());
//    }
//
//    // 查询文档
//    public static void searchDocuments(ElasticsearchClient client, String indexName, String query) throws IOException {
//        SearchRequest request = new SearchRequest.Builder()
//                .index(indexName)
//                .query(q -> q
//                        .match(t -> t
//                                .field("name")
//                                .query(query)
//                        )
//                )
//                .build();
//        SearchResponse<String> response = client.search(request, String.class);
//        List<Hit<String>> hits = response.hits().hits();
//        for (Hit<String> hit : hits) {
//            System.out.println("Found document: " + hit.source());
//        }
//    }
//
//    // 删除文档
//    public static void deleteDocument(ElasticsearchClient client, String indexName, String id) throws IOException {
//        DeleteRequest request = new DeleteRequest.Builder()
//                .index(indexName)
//                .id(id)
//                .build();
//        DeleteResponse response = client.delete(request);
//        if (response.result().toString().equals("deleted")) {
//            System.out.println("Document deleted with ID: " + id);
//        }
//    }
//
//    // 删除索引
//    public static void deleteIndex(ElasticsearchClient client, String indexName) throws IOException {
//        DeleteIndexRequest request = new DeleteIndexRequest.Builder()
//                .index(indexName)
//                .build();
//        DeleteIndexResponse response = client.indices().delete(request);
//        if (response.acknowledged()) {
//            System.out.println("Index deleted: " + indexName);
//        }
//    }
//}

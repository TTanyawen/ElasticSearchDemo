//package com.example.elasticsearchdemo.demo1;
//
//
//import com.example.elasticsearchdemo.ElasticSearchDemoApplication;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
//import org.springframework.data.elasticsearch.core.SearchHit;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.annotation.Resource;
//import java.util.List;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = ElasticSearchDemoApplication.class)
//public class EsTest {
//
//    @Resource
//    private ElasticSearchService elasticSearchService;
//
//    @Resource
//    private ElasticsearchRestTemplate elasticsearchRestTemplate;
//    /**创建索引和映射*/
//    @Test
//    public void createIndex(){
//
////        elasticsearchTemplate.createIndex(ElasticSearch.class);
////        elasticsearchTemplate.putMapping(ElasticSearch.class);
//    }
//
//    /**添加文档或者修改文档(以id为准)*/
//    @Test
//    public void saveElasticSearch(){
//        ElasticSearch elasticSearch = new ElasticSearch();
//        elasticSearch.setId(1);
//        elasticSearch.setTitle("SpringData ElasticSearch");
//        elasticSearch.setContent("Spring Data ElasticSearch 基于 spring data API 简化 elasticSearch操作，将原始操作elasticSearch的客户端API 进行封装 \n" +
//                "    Spring Data为Elasticsearch Elasticsearch项目提供集成搜索引擎");
//        elasticSearchService.save(elasticSearch);
//    }
//    @Test
//    public void findById(){
//        ElasticSearch byId = elasticSearchService.findById(1);
//        System.out.println(byId);
//    }
//    @Test
//    public void deleteById(){
//        elasticSearchService.deleteById(100);
//
//    }
//    @Test
//    public void count(){
//        long count = elasticSearchService.count();
//        System.out.println(count);
//    }
//    @Test
//    public void existsById(){
//        boolean b = elasticSearchService.existsById(102);
//
//        System.out.println(b);
//    }
//    @Test
//    public void findByTitleOrContent(){
//        List<SearchHit<ElasticSearch>> byTitleOrContent = elasticSearchService.findByTitleOrContent("xxxxxxSpringData","elasticSearch");
//        for (SearchHit<ElasticSearch> elasticSearchService : byTitleOrContent) {
//            List<String> title = elasticSearchService.getHighlightField("title");
//            System.out.println(title);
//            List<String> content = elasticSearchService.getHighlightField("content");
//            System.out.println(content);
//
//        }
//    }
//}
//
//

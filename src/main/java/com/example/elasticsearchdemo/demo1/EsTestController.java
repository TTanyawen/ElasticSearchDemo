package com.example.elasticsearchdemo.demo1;

import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/es")
public class EsTestController {
    @Resource
    private ElasticSearchService elasticSearchService;

    @Resource
    private ElasticsearchRestTemplate elasticsearchRestTemplate;


    /**创建索引和映射*/
    @RequestMapping("/createIndex")
    public void createIndex(){
        elasticsearchRestTemplate.createIndex(ElasticSearch.class);
        elasticsearchRestTemplate.putMapping(ElasticSearch.class);
    }

    /**添加文档或者修改文档(以id为准)*/

    @RequestMapping("/save")
    public void saveElasticSearch(){
        ElasticSearch elasticSearch = new ElasticSearch();
        elasticSearch.setId(1);
        elasticSearch.setTitle("SpringData ElasticSearch");
        elasticSearch.setContent("Spring Data ElasticSearch 基于 spring data API 简化 elasticSearch操作，将原始操作elasticSearch的客户端API 进行封装 \n" +
                "    Spring Data为Elasticsearch Elasticsearch项目提供集成搜索引擎");
        elasticSearchService.save(elasticSearch);
    }


    @RequestMapping("/findById")
    public void findById(){
        ElasticSearch byId = elasticSearchService.findById(1);
        System.out.println(byId);
    }

    @RequestMapping("/deleteById")
    public void deleteById(){
        elasticSearchService.deleteById(100);

    }
    @RequestMapping("/count")
    public long count(){
        long count = elasticSearchService.count();
        System.out.println(count);
        return count;
    }

    @RequestMapping("/existsById")
    public boolean existsById(){
        boolean b = elasticSearchService.existsById(102);

        System.out.println(b);
        return b;
    }
    @RequestMapping("/findByTitleOrContent")
    public void findByTitleOrContent(){
        List<SearchHit<ElasticSearch>> byTitleOrContent = elasticSearchService.findByTitleOrContent("xxxxxxSpringData","elasticSearch");
        for (SearchHit<ElasticSearch> elasticSearchService : byTitleOrContent) {
            List<String> title = elasticSearchService.getHighlightField("title");
            System.out.println(title);
            List<String> content = elasticSearchService.getHighlightField("content");
            System.out.println(content);

        }
    }
}

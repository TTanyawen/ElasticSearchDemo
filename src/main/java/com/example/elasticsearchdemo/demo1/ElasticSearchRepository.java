package com.example.elasticsearchdemo.demo1;


import org.springframework.data.elasticsearch.annotations.Highlight;
import org.springframework.data.elasticsearch.annotations.HighlightField;
import org.springframework.data.elasticsearch.annotations.HighlightParameters;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ElasticSearchRepository extends ElasticsearchRepository<ElasticSearch, Integer> {

    /**
     * 查询内容标题查询
     * @param title 标题
     * @param content 内容
     * @return 返回关键字高亮的结果集
     */
    @Highlight(
            fields = {@HighlightField(name = "title"), @HighlightField(name = "content")},
            parameters = @HighlightParameters(preTags = {"<span style='color:red'>"}, postTags = {"</span>"}, numberOfFragments = 0)
    )
    List<SearchHit<ElasticSearch>> findByTitleOrContent(String title, String content);

}



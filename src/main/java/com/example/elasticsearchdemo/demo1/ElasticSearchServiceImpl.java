package com.example.elasticsearchdemo.demo1;


import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class ElasticSearchServiceImpl implements ElasticSearchService {

    @Resource
    private ElasticSearchRepository ElasticSearchRepository;


    @Override
    public void save(ElasticSearch ElasticSearch) {
        ElasticSearchRepository.save(ElasticSearch);
    }

    @Override
    public ElasticSearch findById(Integer id) {
        return ElasticSearchRepository.findById(id).orElse(new ElasticSearch());
    }

    @Override
    public void deleteById(Integer id) {
        ElasticSearchRepository.deleteById(id);
    }

    @Override
    public long count() {
        return ElasticSearchRepository.count();
    }

    @Override
    public boolean existsById(Integer id) {
        return ElasticSearchRepository.existsById(id);
    }

    @Override
    public List<SearchHit<ElasticSearch>> findByTitleOrContent(String title, String content) {
        return ElasticSearchRepository.findByTitleOrContent(title,content);
    }

}



package com.nowcoder.community.service;

import com.alibaba.fastjson.JSONObject;
import com.nowcoder.community.dao.elasticsearch.DiscussPostRepository;
import com.nowcoder.community.entity.DiscussPost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ElasticsearchService
 * @Description
 * @Author cjx
 * @Date 2022/10/14 23:27
 * @Version 1.0
 */
@Service
public class ElasticsearchService {
    @Autowired
    private DiscussPostRepository discussPostRepository;

    @Qualifier("client")
    @Autowired
    private RestHighLevelClient client;

    //存储新的帖子
    public void saveDiscussPost(DiscussPost post){
        discussPostRepository.save(post);
    }

    //删除帖子
    public void deleteDiscussPost(int id){
        discussPostRepository.deleteById(id);
    }


    /**
     * 搜索帖子
     * 返回帖子的列表
     * @param keyword
     * @param current
     * @param limit
     * @return
     */
    public List<DiscussPost> searchDiscussPost(String keyword,int current,int limit) throws IOException {
            SearchRequest searchRequest = new SearchRequest("discusspost");
            //构建搜索条件
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder()
                    //在discusspost索引的title和content字段都查询keyword
                    .query(QueryBuilders.multiMatchQuery(keyword, "title", "content"))
                    .sort(SortBuilders.fieldSort("type").order(SortOrder.DESC))
                    .sort(SortBuilders.fieldSort("score").order(SortOrder.DESC))
                    .sort(SortBuilders.fieldSort("createTime").order(SortOrder.DESC))
                    .from(current*limit)
                    .size(limit)
                    //这一部分是指向高亮显示
                    .highlighter(
                            new HighlightBuilder().field("title").field("content")
                                    .preTags("<em>").postTags("</em>").requireFieldMatch(false)
                    );

            searchRequest.source(searchSourceBuilder);
            List<DiscussPost> list=new ArrayList<>();
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            for (SearchHit hit : searchResponse.getHits().getHits()) {
                DiscussPost discussPost = JSONObject.parseObject(hit.getSourceAsString(), DiscussPost.class);
                //处理高亮显示的结果
                //对title部分高亮显示
                HighlightField titleField = hit.getHighlightFields().get("title");
                if(titleField!=null){
                    discussPost.setTitle(titleField.getFragments()[0].toString());
                }

                HighlightField contentField = hit.getHighlightFields().get("content");
                //对content高亮部分显示
                if(contentField!=null){
                    discussPost.setContent(contentField.getFragments()[0].toString());
                }
                list.add(discussPost);
            }
            return list;
    }

    /**
     * 返回当前关键字匹配的全部数据数量
     * @param keyword
     * @return
     * @throws IOException
     */
    public int getDiscussPostCount(String keyword) throws IOException {
        SearchRequest searchRequest = new SearchRequest("discusspost");
        //构建搜索条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder()
                //在discusspost索引的title和content字段都查询keyword
                .query(QueryBuilders.multiMatchQuery(keyword, "title", "content"));
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        //使用searchResponse.getHits().getTotalHits()得到所有满足条件的hit的数量
        return (int) searchResponse.getHits().getTotalHits().value;
    }
}

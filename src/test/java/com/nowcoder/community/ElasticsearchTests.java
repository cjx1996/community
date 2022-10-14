package com.nowcoder.community;

import com.alibaba.fastjson.JSONObject;
import com.nowcoder.community.dao.DiscussPostMapper;
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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName ElasticsearchTests
 * @Description
 * @Author cjx
 * @Date 2022/10/13 23:01
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class ElasticsearchTests {
    //从数据库中取数据
    @Autowired
    private DiscussPostMapper discussPostMapper;

    //调用Rs的库
    @Autowired
    private DiscussPostRepository discussPostRepository;

    @Qualifier("client")
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    //往es服务器添加数据
    @Test
    public void testInsert() {
        discussPostRepository.save(discussPostMapper.selectDiscussPostById(241));
        discussPostRepository.save(discussPostMapper.selectDiscussPostById(242));
        discussPostRepository.save(discussPostMapper.selectDiscussPostById(243));
    }

    //往es服务器添加多条数据
    @Test
    public void testInsertList() {
        discussPostRepository.saveAll(discussPostMapper.selectDiscussPosts(101, 0, 100));
        discussPostRepository.saveAll(discussPostMapper.selectDiscussPosts(102, 0, 100));
        discussPostRepository.saveAll(discussPostMapper.selectDiscussPosts(103, 0, 100));
        discussPostRepository.saveAll(discussPostMapper.selectDiscussPosts(111, 0, 100));
        discussPostRepository.saveAll(discussPostMapper.selectDiscussPosts(112, 0, 100));
        discussPostRepository.saveAll(discussPostMapper.selectDiscussPosts(131, 0, 100));
        discussPostRepository.saveAll(discussPostMapper.selectDiscussPosts(132, 0, 100));
        discussPostRepository.saveAll(discussPostMapper.selectDiscussPosts(133, 0, 100));
        discussPostRepository.saveAll(discussPostMapper.selectDiscussPosts(134, 0, 100));
    }

    //修改数据。通过覆盖的方式修改，还是调用save()方法
    @Test
    public void testUpdate() {
        DiscussPost discussPost = discussPostMapper.selectDiscussPostById(231);
        discussPost.setContent("我是新人，使劲灌水");
        discussPostRepository.save(discussPost);
    }

    //删除数据。删除某条和删除全部
    @Test
    public void testDelete() {
        discussPostRepository.deleteById(231);
        //删除索引的所有数据，危险！一般不用
//        discussPostRepository.deleteAll();
    }

    //搜索！！！无高亮
    @Test
    public void testSearchByRepository() throws IOException {
        SearchRequest searchRequest = new SearchRequest("discusspost");

        //构建搜索条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder()
                //在discusspost索引的title和content字段都查询“互联网寒冬"
                .query(QueryBuilders.multiMatchQuery("互联网寒冬", "title", "content"))
                //matchQuery是模糊查询，会对key进行分词
//                .query(QueryBuilders.matchQuery(key,value));
                //termQuery是精确查询
//                .query(QueryBuilders.termQuery(key,value))
                .sort(SortBuilders.fieldSort("type").order(SortOrder.DESC))
                .sort(SortBuilders.fieldSort("score").order(SortOrder.DESC))
                .sort(SortBuilders.fieldSort("createTime").order(SortOrder.DESC))
                //可选项。控制允许搜索的时间
//                .timeout(new TimeValue(60, TimeUnit.SECONDS))
                .from(0)
                .size(10);
                //这一部分是指向高亮显示
//                .highlighter(
//                        new HighlightBuilder().field("title").field("content")
//                                .preTags("<em>").postTags("</em>")
//                );

        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        //将结果转化为JSON格式后输出
//        System.out.println(JSONObject.toJSON(searchResponse));

        List<DiscussPost> list = new LinkedList<>();
        for (SearchHit hit : searchResponse.getHits().getHits()) {
            DiscussPost discussPost = JSONObject.parseObject(hit.getSourceAsString(), DiscussPost.class);
            System.out.println(discussPost);
            list.add(discussPost);
        }


    }
    //搜索！！！有高亮显示
    @Test
    public void testSearchByRepositoryWithHighlight() throws IOException {
        SearchRequest searchRequest = new SearchRequest("discusspost");

        //构建搜索条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder()
                //在discusspost索引的title和content字段都查询“互联网寒冬"
                .query(QueryBuilders.multiMatchQuery("互联网寒冬", "title", "content"))
                //matchQuery是模糊查询，会对key进行分词
//                .query(QueryBuilders.matchQuery(key,value));
                //termQuery是精确查询
//                .query(QueryBuilders.termQuery(key,value))
                .sort(SortBuilders.fieldSort("type").order(SortOrder.DESC))
                .sort(SortBuilders.fieldSort("score").order(SortOrder.DESC))
                .sort(SortBuilders.fieldSort("createTime").order(SortOrder.DESC))
                //可选项。控制允许搜索的时间
//                .timeout(new TimeValue(60, TimeUnit.SECONDS))
                .from(0)
                .size(10)
                //这一部分是指向高亮显示
                .highlighter(
                        new HighlightBuilder().field("title").field("content")
                                .preTags("<em>").postTags("</em>").requireFieldMatch(false)
                );

        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        //将结果转化为JSON格式后输出
//        System.out.println(JSONObject.toJSON(searchResponse));

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
            System.out.println(discussPost);
        }
    }


}

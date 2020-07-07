package dyliang;

import com.alibaba.fastjson.JSON;
import dyliang.domain.Person;
import net.minidev.json.JSONArray;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class DyliangApplicationTests {

    @Autowired
    RestHighLevelClient restHighLevelClient;

    /*
        创建索引
     */
    @Test
    public void testCreateIndex() throws IOException {
        // 创建索引请求
        CreateIndexRequest request = new CreateIndexRequest("test.index");
        // 客户端执行请求IndicesClient，请求获得响应
        CreateIndexResponse createIndexResponse = restHighLevelClient.indices().
                create(request, RequestOptions.DEFAULT);
        System.out.println(createIndexResponse);
        // org.elasticsearch.client.indices.CreateIndexResponse@274eb315
    }

    /*
        测试索引是否存在
     */
    @Test
    public void testIndexExist() throws IOException {
        GetIndexRequest request = new GetIndexRequest("test.index");
        boolean exists = restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
        System.out.println(exists); // true
    }

    /*
        测试删除索引
     */
    @Test
    public void testIndexDelete() throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest("test.index");
        AcknowledgedResponse delete = restHighLevelClient.indices().delete(request,
                RequestOptions.DEFAULT);
        System.out.println(delete.isAcknowledged());  // true
    }

    /*
        测试添加文档
     */
    @Test
    public void testAddDoc() throws IOException {
        Person person = Person.builder().name("kobe").age(18).build();
        // 创建请求
        IndexRequest request = new IndexRequest("test.index");
        request.id("1");
        request.timeout(TimeValue.timeValueMinutes(1));
        request.timeout("1s");

        // 将数据放入请求
        request.source(JSON.toJSONString(person), XContentType.JSON);

        // 客户端发送请求 , 获取响应的结果
        IndexResponse indexResponse = restHighLevelClient.index(request,
                RequestOptions.DEFAULT);
        System.out.println(indexResponse.toString());
        System.out.println(indexResponse.status());
        /*
        IndexResponse[index=test.index,type=_doc,id=1,version=2,
            result=updated,seqNo=1,primaryTerm=1,shards={"total":2,"successful":1,"failed":0}]
        OK
         */
    }

    /*
        判断文档是否存在
     */
    @Test
    public void testFetDoc() throws IOException {
        GetRequest getRequest = new GetRequest("test.index", "1");

        getRequest.fetchSourceContext(new FetchSourceContext(false));
        getRequest.storedFields("_none_");
        boolean exists = restHighLevelClient.exists(getRequest, RequestOptions.DEFAULT);
        System.out.println(exists);  // true
    }

    // 获得文档的信息
    @Test
    void testGetDocInfo() throws IOException {
        GetRequest getRequest = new GetRequest("test.index", "1");
        GetResponse getResponse = restHighLevelClient.get(getRequest,
                RequestOptions.DEFAULT);
        System.out.println(getResponse.getSourceAsString());
        System.out.println(getResponse);

        /*
        {"age":18,"name":"kobe"}
        {"_index":"test.index","_type":"_doc","_id":"1","_version":2,"_seq_no":1,
            "_primary_term":1,"found":true,"_source":{"age":18,"name":"kobe"}}
         */
    }

    /*
        更新文档信息
     */
    @Test
    void testUpdateRequest() throws IOException {
        UpdateRequest updateRequest = new UpdateRequest("test.index","1");
        updateRequest.timeout("1s");
        Person person = Person.builder().name("James").age(34).build();
        updateRequest.doc(JSON.toJSONString(person),XContentType.JSON);
        UpdateResponse updateResponse = restHighLevelClient.update(updateRequest,
                RequestOptions.DEFAULT);
        System.out.println(updateResponse.status());
    }

    // 删除文档记录
    @Test
    void testDeleteRequest() throws IOException {
        DeleteRequest request = new DeleteRequest("test.index","1");
        request.timeout("1s");
        DeleteResponse deleteResponse = restHighLevelClient.delete(request,
                RequestOptions.DEFAULT);
        System.out.println(deleteResponse.status());
    }

    @Test
    public void testBulkRequest() throws IOException{
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("1s");

        List<Person> list = new ArrayList<>();
        Collections.addAll(list, new Person("Kobe", 18),
                new Person("James", 34),
                new Person("Ball", 24));

        for (int i = 0; i < list.size(); i++) {
            bulkRequest.add(new IndexRequest("test.index").id(" " + i + 1)
                    .source(JSON.toJSONString(list.get(i)),XContentType.JSON));
        }

        BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(bulkResponse.hasFailures());
    }

    @Test
    void testSearch() throws IOException {
        SearchRequest searchRequest = new SearchRequest("test.index");
        // 构建搜索条件
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.highlighter();
        // 查询条件，我们可以使用 QueryBuilders 工具来实现
        // QueryBuilders.termQuery 精确
        // QueryBuilders.matchAllQuery() 匹配所有
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("age", "18");
//        // MatchAllQueryBuilder matchAllQueryBuilder =
//        QueryBuilders.matchAllQuery();
        sourceBuilder.query(termQueryBuilder);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest,
                RequestOptions.DEFAULT);
        System.out.println(JSON.toJSONString(searchResponse.getHits()));
        System.out.println("=================================");
        for (SearchHit documentFields : searchResponse.getHits().getHits()) {
            System.out.println(documentFields.getSourceAsMap());
        }

        /*
        {"fragment":true,"hits":[{"fields":{},"fragment":false,"highlightFields":{},
        "id":" 01","matchedQueries":[],"primaryTerm":0,"rawSortValues":[],
        "score":1.0,"seqNo":-2,"sortValues":[],"sourceAsMap":{"name":"Kobe","age":18},
        "sourceAsString":"{\"age\":18,\"name\":\"Kobe\"}",
        "sourceRef":{"fragment":true},"type":"_doc","version":-1}],
        "maxScore":1.0,"totalHits":{"relation":"EQUAL_TO","value":1}}
        =================================
        {name=Kobe, age=18}
         */
    }
}

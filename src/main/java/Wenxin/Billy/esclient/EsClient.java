package Wenxin.Billy.esclient;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.ImmutableSettings.Builder;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

public class EsClient implements AutoCloseable {
	private Client client = null;

	/**
	 * 
	 * <p>
	 * 创建索引
	 * </p>
	 * 
	 * @author Billy Dai Created [2015-02-13 下午5:38:22]
	 * @throws Exception
	 */
	void open(String clusterName, String hostname, int port, boolean sniff) {
		Builder builder = ImmutableSettings.settingsBuilder();
		if (clusterName != null && clusterName.length() > 0) {
			// find by cluster name
			builder.put("cluster.name", clusterName);
		}
		if (sniff) {
			// find all node in the cluster
			builder.put("client.transport.sniff", true);
		}
		// start to build setting
		Settings settings = builder.build();
		// addr.getHostAddress()
		client = new TransportClient(settings);
		if (hostname != null && hostname.length() > 0) {
			// add transport address
			((TransportClient) client)
					.addTransportAddress(new InetSocketTransportAddress(
							hostname, port));
		}
	}

	/**
	 * 
	 * <p>
	 * 创建索引
	 * </p>
	 * 
	 * @author Billy Dai Created [2015-02-13 下午5:38:22]
	 * @return 
	 * @throws Exception
	 */
	IndexResponse createIndex(String indexName, String typeName,XContentBuilder doc) throws IOException { 
		IndexResponse response = client.prepareIndex(indexName, typeName)
				.setSource(doc).execute().actionGet();
		// System.out.println(response.getId() + "====" + response.getIndex()
		// + "====" + response.getType()); 
		return response;
	}

	/**
	 * 
	 * <p>
	 * 创建索引
	 * </p>
	 * 
	 * @author Billy Dai Created [2015-02-13 下午5:38:22]
	 * @throws Exception
	 */
	void searchIndex(String indexName) throws IOException {
		QueryBuilder qb = QueryBuilders.termQuery("title", "this");
		// first page
		SearchResponse scrollResp = client.prepareSearch(indexName)
				.setSearchType(SearchType.SCAN).setScroll(new TimeValue(60000))
				.setQuery(qb).setSize(100).execute().actionGet();
		// System.out.println(indexName + ": contextSize="
		// + scrollResp.contextSize() + " totalShards="
		// + scrollResp.getTotalShards() + " restStatus="
		// + scrollResp.status());
		while (true) {
			// scroll all
			scrollResp = client.prepareSearchScroll(scrollResp.getScrollId())
					.setScroll(new TimeValue(600000)).execute().actionGet();
			for (SearchHit hit : scrollResp.getHits()) {
				Map<String, Object> source = hit.getSource();
				if (!source.isEmpty()) {
					for (Iterator<Map.Entry<String, Object>> it = source
							.entrySet().iterator(); it.hasNext();) {
						@SuppressWarnings("unused")
						Map.Entry<String, Object> entry = it.next();
						// System.out.println(entry.getKey() + "======="
						// + entry.getValue());
					}
				}
			}
			if (scrollResp.getHits().hits().length == 0) {
				break;
			}
		}
		System.out.println();
	}

	public void close() throws Exception {
		if (client != null) {
			client.close();
		}
	}
}

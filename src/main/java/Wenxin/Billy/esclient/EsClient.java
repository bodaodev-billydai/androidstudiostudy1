package Wenxin.Billy.esclient;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import static org.elasticsearch.node.NodeBuilder.*;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.*;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.ImmutableSettings.Builder;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.*;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.node.*;
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
	 * @throws Exception
	 */
	void createIndex() throws IOException {
		XContentBuilder doc;
		doc = XContentFactory.jsonBuilder().startObject()
				.field("title", "this is a title!")
				.field("description", "descript what?").field("price", 100)
				.field("onSale", true).field("type", 1)
				.field("createDate", new Date()).endObject();
		IndexResponse response = client
				.prepareIndex("productindex", "productType").setSource(doc)
				.execute().actionGet();
		System.out.println(response.getId() + "====" + response.getIndex()
				+ "====" + response.getType());
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
	void searchIndex() throws IOException {
		QueryBuilder qb = QueryBuilders.termQuery("title", "this");
		SearchResponse scrollResp = client.prepareSearch("productindex")
				.setSearchType(SearchType.SCAN).setScroll(new TimeValue(60000))
				.setQuery(qb).setSize(100).execute().actionGet();

		while (true) {
			scrollResp = client.prepareSearchScroll(scrollResp.getScrollId())
					.setScroll(new TimeValue(600000)).execute().actionGet();
			for (SearchHit hit : scrollResp.getHits()) {
				Map<String, Object> source = hit.getSource();
				if (!source.isEmpty()) {
					for (Iterator<Map.Entry<String, Object>> it = source
							.entrySet().iterator(); it.hasNext();) {
						Map.Entry<String, Object> entry = it.next();
						System.out.println(entry.getKey() + "======="
								+ entry.getValue());

					}
				}

			}
			if (scrollResp.getHits().hits().length == 0) {
				break;
			}

		}
	}

	public void close() throws Exception {
		if (client != null) {
			client.close();
		}
	}
}

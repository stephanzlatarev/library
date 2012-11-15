package library;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CountingProxy implements CatalogSearch {

	private Map<String, Integer> statistics;
	private CatalogSearch delegate;

	public CountingProxy(CatalogSearch search) {
		this.delegate = search;
		this.statistics = new HashMap<String, Integer>();
	}

	public Collection<BookTitle> search(String keyword) {
		Collection<BookTitle> result = delegate.search(keyword);

		statistics.put(keyword, result.size());

		return result;
	}

	public Map<String, Integer> getSearchStatistics() {
		return statistics;
	}

}
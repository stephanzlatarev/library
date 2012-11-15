package library;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

/**
 * The catalog browser provides a search service into the catalog of the library
 */
public class CatalogBrowser {

	private CatalogSearch[] catalogs;
	private HashMap<String, Integer> statistics;

	public CatalogBrowser(CatalogSearch... catalogs) {
		this.catalogs = catalogs;
		this.statistics = new HashMap<String, Integer>();
	}

	public Collection<BookTitle> search(String keyword) {
		Collection<BookTitle> result = new HashSet<BookTitle>();

		for (CatalogSearch catalog: catalogs) {
			if (catalog instanceof TheDigitalLibraryCatalogSearch) {
				statistics.put(keyword, catalog.search(keyword).size());
			}

			result.addAll(catalog.search(keyword));
		}

		return result;
	}

	public HashMap<String, Integer> getSearchStatistics() {
		return statistics;
	}

}
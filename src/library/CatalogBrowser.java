package library;

import java.util.Collection;
import java.util.HashSet;

/**
 * The catalog browser provides a search service into the catalog of the library
 */
public class CatalogBrowser {

	private CatalogSearch[] catalogs;

	private static Object lock = new Object();

	public CatalogBrowser(CatalogSearch... catalogs) {
		this.catalogs = catalogs;
	}

	public Collection<BookTitle> search(String keyword) {
	  synchronized (lock) {
	    Collection<BookTitle> result = new HashSet<BookTitle>();

	    for (CatalogSearch catalog: catalogs) {
	      result.addAll(catalog.search(keyword));
	    }

	    return result;
	  }
	}

}
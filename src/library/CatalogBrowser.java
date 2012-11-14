package library;

import java.util.Collection;
import java.util.HashSet;

/**
 * The catalog browser provides a search service into the catalog of the library
 */
public class CatalogBrowser {

	private Catalog catalog;

	public CatalogBrowser(Catalog catalog) {
		this.catalog = catalog;
	}

	public Collection<BookTitle> search(String keyword) {
		Collection<BookTitle> result = new HashSet<BookTitle>();

		for (BookTitle title: catalog.getTitles()) {
			if (title.getTitle().contains(keyword)) {
				result.add(title);
			}
		}

		return result;
	}

}
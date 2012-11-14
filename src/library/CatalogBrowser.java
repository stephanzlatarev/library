package library;

import java.util.Collection;
import java.util.HashSet;

/**
 * The catalog browser provides a search service into the catalog of the library
 */
public class CatalogBrowser {

	private Object catalog;

	public CatalogBrowser(Catalog catalog) {
		this.catalog = catalog;
	}

	public CatalogBrowser(TheDigitalLibraryCatalogBrowser catalog) {
		this.catalog = catalog;
	}

	public Collection<BookTitle> search(String keyword) {
		if (catalog instanceof Catalog) {
			Collection<BookTitle> result = new HashSet<BookTitle>();

			for (BookTitle title: ((Catalog) catalog).getTitles()) {
				if (title.getTitle().contains(keyword)) {
					result.add(title);
				}
			}

			return result;
		} else {
			TheDigitalLibraryCatalogBrowser tdl = (TheDigitalLibraryCatalogBrowser) catalog;

			tdl.connect();
			try {
				return tdl.search(keyword);
			} finally {
				tdl.disconnect();
			}
		}
	}

}